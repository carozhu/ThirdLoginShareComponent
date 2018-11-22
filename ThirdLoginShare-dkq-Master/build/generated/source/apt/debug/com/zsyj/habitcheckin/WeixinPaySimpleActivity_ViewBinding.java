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

public class WeixinPaySimpleActivity_ViewBinding implements Unbinder {
  private WeixinPaySimpleActivity target;

  private View view2131230917;

  @UiThread
  public WeixinPaySimpleActivity_ViewBinding(WeixinPaySimpleActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WeixinPaySimpleActivity_ViewBinding(final WeixinPaySimpleActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.weixin_pay, "method 'weixin_pay'");
    view2131230917 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.weixin_pay();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view2131230917.setOnClickListener(null);
    view2131230917 = null;
  }
}
