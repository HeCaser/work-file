package com.example.xqtest.scrollabletable;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


;import com.example.xqtest.R;

/**
 * A flexible view for providing a limited window into a large data set,like a two-dimensional RecyclerView.
 * but it will pin the itemView of first row and first column in their original location.
 */
public class ScrollableTable extends FrameLayout {

    protected LinearLayout parent;
    public RecyclerView rowRecyclerView;
    public RecyclerView headerRecyclerView;
    protected TableRowAdapter tableRowAdapter;
    protected TableAdapter tableAdapter;
    protected FrameLayout firstItemView;
    protected View headerRow;

    private RowClickListener rowClickListener;
    private HeaderClickListener headerClickListener;
    private FooterClickListener footerClickListener;
    private OnHorizontalScrollListener onHorizontalScrollListener;


    private int firstPos = -1; //滑动位置标记
    private int firstOffset = -1; //滑动位置标记

    private static float[] actionDownLocation = new float[2];
    private static long actionDownTime;

    private int rowFooterResId = -1;
    private int footerSize = 0;

    private boolean isTouching = false;
    private boolean isVerticalBarEnabled = false;
    private Handler mHandler = new Handler();
    private RecyclerView mCurrentRVTouched = null;
    private View footerView;

    public View getFooterView() {
        return footerView;
    }

    public ScrollableTable(Context context, TableAdapter tableAdapter) {
        super(context);
        this.tableAdapter = tableAdapter;
        initView();
    }

    public ScrollableTable(Context context) {
        super(context);
        initView();
    }

    public ScrollableTable(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ScrollableTable(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void setTableAdapter(TableAdapter tableAdapter) {
        this.tableAdapter = tableAdapter;
        if (this.tableRowAdapter != null) {
            tableRowAdapter.setTableAdapter(tableAdapter);
            tableRowAdapter.notifyDataSetChanged();
        } else {
            tableRowAdapter = new TableRowAdapter(tableAdapter);
            tableRowAdapter.setHasStableIds(true);
            rowRecyclerView.setAdapter(tableRowAdapter);
        }

        initHeadRow();
    }

    private void initHeadRow() {
        initHeaderFirstItemView(tableAdapter);
        initHeaderRecyclerView();
    }

    public void headerNotifyDataSetChanged() {
        if (tableAdapter == null || !tableAdapter.hasHeadRow()) {
            headerRow.setVisibility(View.GONE);
            return;
        }
        initHeaderFirstItemView(tableAdapter);
        if (headerRecyclerView.getAdapter() == null) {
            initHeaderRecyclerView();
        } else {
            headerRecyclerView.getAdapter().notifyDataSetChanged();
        }
    }

    public void contentNotifyDataSetChanged() {
        if (tableRowAdapter != null) {
            tableRowAdapter.notifyDataSetChanged();
        }
    }

    public void notifyDataSetChanged() {
        headerNotifyDataSetChanged();
        contentNotifyDataSetChanged();
    }

    public void scrollToPosition(int pos) {
        if (rowRecyclerView != null) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) rowRecyclerView.getLayoutManager();
            linearLayoutManager.scrollToPositionWithOffset(pos, 0);
        }
    }

    // 横向滑动到指定位置
    public void scrollToPositionHorizontal(int pos) {
        if (headerRecyclerView != null) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) headerRecyclerView.getLayoutManager();
            linearLayoutManager.scrollToPositionWithOffset(pos, 0);
            headerRecyclerView.post(new Runnable() {
                @Override
                public void run() {
                    syncLocation(headerRecyclerView);
                }
            });
        }
    }

    public void addHeaderDivider(View view) {
        parent.addView(view, 1);
    }

    public void addContentDivider(DividerItemDecoration dividerItemDecoration) {
        rowRecyclerView.addItemDecoration(dividerItemDecoration);
    }

    protected int getResId() {
        return R.layout.scrollable_table;
    }

    protected void initView() {
        LayoutInflater.from(getContext()).inflate(getResId(), this, true);
        parent = findViewById(R.id.scroll_table_parent);
        rowRecyclerView = (RecyclerView) findViewById(R.id.content_recycler);
        rowRecyclerView.setLayoutManager(new CommonLinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        headerRow = (View) findViewById(R.id.header_row);
        firstItemView = (FrameLayout) findViewById(R.id.row_first_column);
        headerRecyclerView = (RecyclerView) findViewById(R.id.row_recycler);
        headerRecyclerView.setLayoutManager(new CommonLinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        headerRecyclerView.setHasFixedSize(true);
        initRVScrollEvent(headerRecyclerView);

        if (tableAdapter != null) {
            setTableAdapter(tableAdapter);
        }

        rowRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int x = recyclerView.computeVerticalScrollOffset();
                int y = recyclerView.computeHorizontalScrollOffset();
                System.out.println("hepan y = " + y + " dy =" + dy);
                changeHider(y, dy);

                syncAllRV(null);
            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                verticalScrollBarState();
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int y = recyclerView.computeVerticalScrollOffset();
                    System.out.println("hepan y = 停止滑动 "+ y);
                    changeHider(y, 0);
                }
            }
        });
        rowRecyclerView.setVerticalScrollBarEnabled(true);
        parent.setMotionEventSplittingEnabled(false);
        rowRecyclerView.setMotionEventSplittingEnabled(false);
    }

    int preDy = 0;

    private void changeHider(int y, int dy) {
        Context ctx = getContext();
        if (ctx instanceof Activity) {
            Activity activity = (Activity) ctx;
            View view = activity.findViewById(R.id.tvTip);
            RelativeLayout.LayoutParams laram = (RelativeLayout.LayoutParams) view.getLayoutParams();
            int yy = Math.min(y, 100);
            if (preDy < 0 && dy > 0) {
                preDy = dy;
                return;
            }
            if (preDy > 0 && dy < 0) {
                preDy = dy;
                return;
            }
            preDy = dy;

//
//            laram.setMargins(0, -yy, 0, 0);


        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN || ev.getAction() == MotionEvent.ACTION_MOVE) {
            isTouching = true;
        } else {
            isTouching = false;
        }
        return super.onInterceptTouchEvent(ev);
    }

    private void resizeFirstColumn(View view, int row) {
        if (tableAdapter != null) {
            if (view.getLayoutParams().width != tableAdapter.getItemViewWidth(row, tableAdapter.getColumnStart())) {
                view.getLayoutParams().width = tableAdapter.getItemViewWidth(row, tableAdapter.getColumnStart());
            }
        }
    }

    private void resizeFirstColumnHeight(TableRowAdapter.ViewHolder holder, int row) {
        if (tableAdapter != null && tableAdapter.isFirstColumnViewHeight()) {
            if (holder.firstColumnItemView != null) {
                holder.firstColumnItemView.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
                holder.firstColumnItemView.post(new Runnable() {
                    @Override
                    public void run() {
                        if (holder.background.getLayoutParams().height != holder.firstColumnItemView.getHeight()) {
                            holder.background.getLayoutParams().height = holder.firstColumnItemView.getHeight();
                            holder.followBackground.getLayoutParams().height = holder.firstColumnItemView.getHeight();
                            holder.itemView.requestLayout();
                        }
                    }
                });
            }
        }
    }

    private void initHeaderRecyclerView() {
        if (tableAdapter != null && tableAdapter.hasHeadRow()) {
            if (headerRecyclerView.getAdapter() == null) {
                TableRowItemAdapter rowItemAdapter = new TableRowItemAdapter(0 + tableAdapter.getRowStart(), tableAdapter, headerRecyclerView);
                headerRecyclerView.setAdapter(rowItemAdapter);
            } else {
                headerNotifyDataSetChanged();
            }
        }
    }

    // 第一行第一列: init or refresh
    private void initHeaderFirstItemView(TableAdapter tableAdapter) {
        if (!tableAdapter.hasHeadRow()) return;
        headerRow.setVisibility(View.VISIBLE);
        int column = 0 + tableAdapter.getColumnStart();
        RecyclerView.ViewHolder viewHolder;
        if (firstItemView.getTag() == null) {
            viewHolder = tableAdapter.onCreateHeaderViewHolder(firstItemView, tableAdapter.getItemViewType(0 + tableAdapter.getRowStart(), column));
            firstItemView.addView(viewHolder.itemView);
            addHeaderItemClickListener(column, viewHolder.itemView);
            firstItemView.setTag(viewHolder);
        } else {
            viewHolder = (RecyclerView.ViewHolder) firstItemView.getTag();
        }
        tableAdapter.onBindHeaderViewHolder(viewHolder, column);
        resizeFirstColumn(firstItemView, tableAdapter.getRowStart());
    }

    private void addHeaderItemClickListener(final int column, final View itemView) {
        itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (headerClickListener != null) {
                    headerClickListener.onClick(column + tableAdapter.getColumnStart(), itemView);
                }
            }
        });
    }

    private RecyclerView scrolling;

    private void initRVScrollEvent(final RecyclerView recyclerView) {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        if (layoutManager != null && firstPos >= 0 && (firstOffsetWhenShowStart < 0 || firstOffset > 0)) {
            correctLocation(recyclerView);
        }

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView == scrolling) {
                    syncLocation(recyclerView);
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    scrolling = recyclerView;
                } else if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    scrolling = null;
                }

                if (onHorizontalScrollListener != null) {
                    onHorizontalScrollListener.onScroll(recyclerView, newState);
                }
            }
        });
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                if (mCurrentRVTouched != null && recyclerView != mCurrentRVTouched) {
                    return true;
                }

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    headerRecyclerView.stopScroll();
                    for (int i = 0; i < rowRecyclerView.getLayoutManager().getChildCount(); i++) {
                        View recycler = rowRecyclerView.getLayoutManager().getChildAt(i).findViewById(R.id.row_recycler);
                        if (recycler instanceof RecyclerView) {
                            ((RecyclerView) recycler).stopScroll();
                        }
                    }
                }
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mCurrentRVTouched = recyclerView;
                        actionDownLocation[0] = motionEvent.getX();
                        actionDownLocation[1] = motionEvent.getY();
                        actionDownTime = System.currentTimeMillis();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mCurrentRVTouched = recyclerView;
                        break;
                    case MotionEvent.ACTION_UP:
                        mCurrentRVTouched = null;
                        if (!isClickOnChildWhichHasListener(recyclerView, motionEvent)) {
                            callClick(recyclerView, motionEvent.getX(), motionEvent.getY());
                        }
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        mCurrentRVTouched = null;
                        break;
                }
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {

            }
        });
    }

    private int firstOffsetWhenShowStart = -1;

    private void syncLocation(RecyclerView recyclerView) {
        computeCurrentLocation(recyclerView);
        syncAllRV(recyclerView);
    }

    private void computeCurrentLocation(RecyclerView recyclerView) {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int firstVisiblePos = linearLayoutManager.findFirstVisibleItemPosition();
        View firstItem = linearLayoutManager.getChildAt(0);
        if (firstItem != null) {
            int firstRight = linearLayoutManager.getDecoratedRight(firstItem);

            firstPos = firstVisiblePos;
            firstOffset = firstRight;
            firstOffsetWhenShowStart = firstRight - getContentItemWidth();
        }
    }

    private void syncAllRV(RecyclerView recyclerView) {
        correctLocation(headerRecyclerView);
        for (int i = 0; i < rowRecyclerView.getLayoutManager().getChildCount(); i++) {
            View recycler = rowRecyclerView.getLayoutManager().getChildAt(i).findViewById(R.id.row_recycler);
            if (recycler instanceof RecyclerView) {
                correctLocation((RecyclerView) recycler);
            }
        }
    }

    private void verticalScrollBarState() {
        if (isVerticalBarEnabled) {
            if (isScrolling()) {
                mHandler.removeCallbacksAndMessages(null);
                rowRecyclerView.setVerticalScrollBarEnabled(true);
            } else {
                mHandler.postDelayed(() -> rowRecyclerView.setVerticalScrollBarEnabled(false), 800);
            }
        }
    }

    private void correctLocation(final RecyclerView recyclerView) {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        View firstVisibleItem = linearLayoutManager.getChildAt(0);
        if (firstVisibleItem != null) {
            correctLocation(linearLayoutManager);
        } else {
            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    correctLocation((LinearLayoutManager) recyclerView.getLayoutManager());
                }
            });
        }
    }

    private void correctLocation(LinearLayoutManager layoutManager) {
        layoutManager.scrollToPositionWithOffset(firstPos + 1, firstOffset);
    }

    private int getContentItemWidth() {
        return tableAdapter.getItemViewWidth(tableAdapter.getRowStart() + 1, tableAdapter.getColumnStart() + 1);
    }

    private boolean isClickOnChildWhichHasListener(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (recyclerView.getChildCount() == 0) return false;
        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            if (recyclerView.getChildAt(i).hasOnClickListeners()) {
                View view = recyclerView.getChildAt(i);
                Rect rect = new Rect();
                view.getGlobalVisibleRect(rect);
                float x = motionEvent.getRawX();
                float y = motionEvent.getRawY();
                if ((x >= rect.left && x <= (rect.right)) && (y >= rect.top && y <= (rect.bottom))) {
                    return true;
                }
            }
        }
        return false;
    }

    private void callClick(View view, float x, float y) {
        if (view == headerRecyclerView) {
            return;
        }
        if (!view.hasOnClickListeners()) return;

        if (System.currentTimeMillis() - actionDownTime < 100) { //100ms内点击有效
            final float offset = 100;
            if (Math.abs(x - actionDownLocation[0]) < offset
                    && Math.abs(y - actionDownLocation[1]) < offset) {
                view.callOnClick();
            }
        }
    }

    public void setFooter(int resId) {
        rowFooterResId = resId;
        footerSize = 1;
    }

    public void clearFooter() {
        rowFooterResId = -1;
        footerSize = 0;
    }

    /**
     * Adapter used to bind dataSet to views that are displayed within a{@link ScrollableTable}.
     */
    private class TableRowAdapter extends RecyclerView.Adapter<TableRowAdapter.ViewHolder> {

        private TableAdapter tableAdapter;
        private final int VIEW_TYPE_NORMAL = 0;
        private final int VIEW_TYPE_FOOTER = 1;

        private final RecyclerView.RecycledViewPool mRecycledViewPool = new RecyclerView.RecycledViewPool();

        public TableRowAdapter(TableAdapter tableAdapter) {
            this.tableAdapter = tableAdapter;
            initHeaderRecyclerView();

        }

        public void setTableAdapter(TableAdapter tableAdapter) {
            this.tableAdapter = tableAdapter;
            initHeaderRecyclerView();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemCount() {
            return tableAdapter.getRowCount() + footerSize;
        }

        private boolean haveFooter() {
            return rowFooterResId > 0;
        }

        private boolean isFooter(int position) {
            return haveFooter() && position == (getItemCount() - 1) && getItemCount() >= 0;
        }

        @Override
        public int getItemViewType(int position) {
            if (isFooter(position)) {
                return VIEW_TYPE_FOOTER;
            } else {
                return VIEW_TYPE_NORMAL;
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == VIEW_TYPE_FOOTER) {
                footerView = LayoutInflater.from(parent.getContext()).inflate(rowFooterResId, parent, false);
                footerView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (footerClickListener != null) {
                            footerClickListener.onClick();
                        }
                    }
                });
                return new ViewHolder(footerView);
            } else {
                ViewHolder viewHolder = new ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.scrollable_table_row, parent, false)
                );
                viewHolder.recyclerView.setItemAnimator(null);
                viewHolder.recyclerView.setRecycledViewPool(mRecycledViewPool);
                viewHolder.recyclerView.setNestedScrollingEnabled(false);
                initRVScrollEvent(viewHolder.recyclerView);
                return viewHolder;
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (isFooter(position)) return;

            final int row = position + 1 + tableAdapter.getRowStart();
            final int column = 0 + tableAdapter.getColumnStart();

            TableRowItemAdapter rowItemAdapter = (TableRowItemAdapter) holder.recyclerView.getAdapter();
            if (rowItemAdapter == null) {
                rowItemAdapter = new TableRowItemAdapter(row, tableAdapter, holder.recyclerView);
                rowItemAdapter.setHasStableIds(true);
                holder.recyclerView.setAdapter(rowItemAdapter);
            } else {
                rowItemAdapter.setRow(row);
                rowItemAdapter.notifyDataSetChanged();
            }
            tableAdapter.onBindRow(holder.background, row);
            tableAdapter.onBindFollowBackground(holder.followBackground, row);

            if (holder.firstColumnItemVH == null) {
                RecyclerView.ViewHolder viewHolder = tableAdapter.onCreateViewHolder(holder.firstColumnItemView, tableAdapter.getItemViewType(row, column));
                holder.firstColumnItemVH = viewHolder;
                tableAdapter.onBindViewHolder(holder.firstColumnItemVH, row, column);
                holder.firstColumnItemView.addView(viewHolder.itemView);
                resizeFirstColumn(viewHolder.itemView, row);
                resizeFirstColumnHeight(holder, row);
            } else {
                tableAdapter.onBindViewHolder(holder.firstColumnItemVH, row, column);
                // 首列宽度也是需要动态变化的.
                resizeFirstColumn(holder.firstColumnItemView, row);
                resizeFirstColumnHeight(holder, row);
            }


            OnClickListener listener = new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (rowClickListener != null) {
                        rowClickListener.onClick(row);
                    }
                }
            };
            holder.itemView.setOnClickListener(listener);
            holder.firstColumnItemView.setOnClickListener(listener);
            holder.recyclerView.setOnClickListener(listener);

        }

        class ViewHolder extends RecyclerView.ViewHolder {
            public View background;
            public View followBackground;
            public RecyclerView recyclerView;
            public FrameLayout firstColumnItemView;
            public RecyclerView.ViewHolder firstColumnItemVH;

            public ViewHolder(View view) {
                super(view);
                this.recyclerView = (RecyclerView) view.findViewById(R.id.row_recycler);
                this.background = view.findViewById(R.id.background);
                this.followBackground = view.findViewById(R.id.follow_highlight_background);
                if (recyclerView == null) return;
//                recyclerView.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (recyclerView.getHeight() > 0) {
//                            background.getLayoutParams().height = recyclerView.getHeight();
//                            followBackground.getLayoutParams().height = recyclerView.getHeight();
//                        }
//                    }
//                });
                this.firstColumnItemView = (FrameLayout) view.findViewById(R.id.row_first_column);
                this.recyclerView.setLayoutManager(new CommonLinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
            }
        }

    }

    /**
     * Adapter used to bind dataSet to cell View that are displayed within every row of {@link ScrollableTable}.
     */
    private class TableRowItemAdapter extends RecyclerView.Adapter {

        private RecyclerView recyclerView;
        private TableAdapter tableAdapter;
        private int row;

        public TableRowItemAdapter(int row, TableAdapter tableAdapter, RecyclerView recyclerView) {
            this.row = row;
            this.tableAdapter = tableAdapter;
            this.recyclerView = recyclerView;
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            RecyclerView.ViewHolder holder;
            if (row == this.tableAdapter.getRowStart()) {
                holder = this.tableAdapter.onCreateHeaderViewHolder(parent, viewType);
            } else {
                holder = this.tableAdapter.onCreateViewHolder(parent, viewType);
            }
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            int column = position + 1 + this.tableAdapter.getColumnStart();
            if (row == this.tableAdapter.getRowStart()) {
                this.tableAdapter.onBindHeaderViewHolder(holder, column);
                addHeaderItemClickListener(column, holder.itemView);
            } else {
                correctLocation(recyclerView);
                this.tableAdapter.onBindViewHolder(holder, row, column);
            }
            if (tableAdapter.getItemViewWidth(row, column) >= 0) {
                if (holder.itemView.getLayoutParams().width != tableAdapter.getItemViewWidth(row, column)) {
                    holder.itemView.getLayoutParams().width = tableAdapter.getItemViewWidth(row, column);
                }
            }
        }

        @Override
        public int getItemViewType(int position) {
            return this.tableAdapter.getItemViewType(row, position + 1 + this.tableAdapter.getColumnStart());
        }


        @Override
        public int getItemCount() {
            return tableAdapter.getColumnCount() - 1;
        }

        public void setRow(int row) {
            this.row = row;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
    }

    public TableAdapter getTableAdapter() {
        return tableAdapter;
    }

    public void setRowClickListener(RowClickListener rowClickListener) {
        this.rowClickListener = rowClickListener;
    }

    public void setHeaderClickListener(HeaderClickListener headerClickListener) {
        this.headerClickListener = headerClickListener;
    }

    public void setFooterClickListener(FooterClickListener footerClickListener) {
        this.footerClickListener = footerClickListener;
    }

    public void setFooterVisible(boolean visible) {
        if (footerView != null) {
            footerView.setVisibility(visible ? VISIBLE : INVISIBLE);
        }
    }

    public void setOnScrollListener(OnHorizontalScrollListener onHorizontalScrollListener) {
        this.onHorizontalScrollListener = onHorizontalScrollListener;
    }

    public HeaderClickListener getHeaderClickListener() {
        return headerClickListener;
    }

    public interface RowClickListener {
        void onClick(int row);
    }

    public interface HeaderClickListener {
        void onClick(int column, View itemView);
    }

    public interface OnHorizontalScrollListener {
        void onScroll(RecyclerView recyclerView, int newState);
    }

    public interface FooterClickListener {
        void onClick();
    }

    public void setVerticalScrollBarEnabled(boolean verticalBarEnabled) {
        this.isVerticalBarEnabled = verticalBarEnabled;
    }

    public boolean isScrolling() {
        return rowRecyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE || headerRecyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE;
    }
}
