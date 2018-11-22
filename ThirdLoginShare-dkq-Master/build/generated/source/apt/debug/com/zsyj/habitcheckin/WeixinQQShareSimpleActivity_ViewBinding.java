// Generated code from Butter Knife. Do not modify!
package com.zsyj.habitcheckin;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WeixinQQShareSimpleActivity_ViewBinding implements Unbinder {
  private WeixinQQShareSimpleActivity target;

  private View view2131230840;

  private View view2131230842;

  private View view2131230915;

  private View view2131230914;

  @UiThread
  public WeixinQQShareSimpleActivity_ViewBinding(WeixinQQShareSimpleActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WeixinQQShareSimpleActivity_ViewBinding(final WeixinQQShareSimpleActivity target,
      View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.qq_friend_share, "method 'qq_friend_share'");
    view2131230840 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.qq_friend_share();
      }
    });
    view = Utils.findRequiredView(source, R.id.qq_zone_share, "method 'qq_zone_share'");
    view2131230842 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.qq_zone_share();
      }
    });
    view = Utils.findRequiredView(source, R.id.weixin_friend_share, "method 'weixin_friend_share'");
    view2131230915 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.weixin_friend_share();
      }
    });
    view = Utils.findRequiredView(source, R.id.weixin_circle_share, "method 'weixin_circle_share'");
    view2131230914 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.weixin_circle_share();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view2131230840.setOnClickListener(null);
    view2131230840 = null;
    view2131230842.setOnClickListener(null);
    view2131230842 = null;
    view2131230915.setOnClickListener(null);
    view2131230915 = null;
    view2131230914.setOnClickListener(null);
    view2131230914 = null;
  }
}
