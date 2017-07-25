package com.dianjiake.android.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dianjiake.android.R;
import com.dianjiake.android.base.BaseFragment;
import com.dianjiake.android.data.bean.ServiceFirstBean;
import com.dianjiake.android.data.bean.ServiceSecondBean;
import com.dianjiake.android.view.widget.ToolbarSpaceView;

import java.util.List;

import butterknife.BindView;
import timber.log.Timber;

/**
 * Created by lfs on 2017/7/25.
 */

public class ServiceFragment extends BaseFragment<ServiceContract.Presenter> implements ServiceContract.View {
    @BindView(R.id.toolbar_space)
    ToolbarSpaceView toolbarSpace;
    @BindView(R.id.toolbar_search_input)
    TextView toolbarSearchInput;
    @BindView(R.id.toolbar_search_holder)
    LinearLayout toolbarSearchHolder;
    @BindView(R.id.toolbar_msg_holder)
    FrameLayout toolbarMsgHolder;
    @BindView(R.id.toolbar_holder)
    LinearLayout toolbarHolder;
    @BindView(R.id.rv_first)
    RecyclerView rvFirst;
    @BindView(R.id.rv_second)
    RecyclerView rvSecond;

    ServiceFirstAdapter firstAdapter;
    LinearLayoutManager firstLayoutManager;

    ServiceSecondAdapter secondAdapter;
    GridLayoutManager secondLayoutManager;

    RecyclerView.OnScrollListener secondScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            checkSecondPosition();
        }
    };

    @Override
    public void setPresenter(ServiceContract.Presenter presenter) {

    }

    @Override
    protected int provideLayout() {
        return R.layout.fragment_service;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firstAdapter = new ServiceFirstAdapter();
        firstLayoutManager = new LinearLayoutManager(view.getContext());
        rvFirst.setLayoutManager(firstLayoutManager);
        rvFirst.setAdapter(firstAdapter);

        secondAdapter = new ServiceSecondAdapter();
        secondLayoutManager = new GridLayoutManager(view.getContext(), 3);
        secondLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return presenter.getServiceSeconds().get(position).getViewType() == ServiceType.NORMAL ? 1 : 3;
            }
        });
        rvSecond.setLayoutManager(secondLayoutManager);
        rvSecond.setAdapter(secondAdapter);

        rvSecond.addOnScrollListener(secondScrollListener);

        initAdapterListener();
        presenter.load();
    }

    private void initAdapterListener() {
        firstAdapter.setListener(new ServiceFirstAdapter.OnFirstClickListener() {
            @Override
            public void onClick(ServiceFirstBean firstBean, int position) {
                for (int i = 0; i < presenter.getServiceSeconds().size(); i++) {
                    ServiceSecondBean bean = presenter.getServiceSeconds().get(i);
                    if (firstBean.getMingcheng().equals(bean.getViewTitle())) {
                        secondScroll(i);
                        break;
                    }
                }
            }
        });
    }

    private void checkSecondPosition() {
        int itemPosition = secondLayoutManager.findFirstVisibleItemPosition();
        String secondTitle = presenter.getServiceSeconds().get(itemPosition).getViewTitle();
        if (itemPosition < presenter.getServiceSeconds().size() && presenter.getServiceSeconds().get(itemPosition).getViewType() == ServiceType.TITLE) {
            for (int i = 0; i < presenter.getServiceFirsts().size(); i++) {
                if (presenter.getServiceFirsts().get(i).getMingcheng().equals(secondTitle)) {
                    firstAdapter.scrollToPosition(i);
                }
            }
        }
    }


    private void secondScroll(int position) {
        RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(getActivity()) {
            @Override
            protected int getVerticalSnapPreference() {
                return LinearSmoothScroller.SNAP_TO_START;
            }
        };
        smoothScroller.setTargetPosition(position);
        secondLayoutManager.startSmoothScroll(smoothScroller);
    }


    @Override
    protected ServiceContract.Presenter getPresenter() {
        return new ServicePresenter(this);
    }


    @Override
    public void onDestroyView() {
        rvSecond.removeOnScrollListener(secondScrollListener);
        super.onDestroyView();

    }

    @Override
    public void setItems(List<ServiceFirstBean> items, List<ServiceSecondBean> seconds) {
        firstAdapter.setItems(items);
        secondAdapter.setItems(seconds);
    }
}
