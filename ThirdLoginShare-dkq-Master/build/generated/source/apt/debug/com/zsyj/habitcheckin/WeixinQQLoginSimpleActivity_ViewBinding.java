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

public class WeixinQQLoginSimpleActivity_ViewBinding implements Unbinder {
  private WeixinQQLoginSimpleActivity target;

  private View view2131230841;

  private View view2131230916;

  @UiThread
  public WeixinQQLoginSimpleActivity_ViewBinding(WeixinQQLoginSimpleActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WeixinQQLoginSimpleActivity_ViewBinding(final WeixinQQLoginSimpleActivity target,
      View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.qq_login, "method 'qq_login'");
    view2131230841 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.qq_login();
      }
    });
    view = Utils.findRequiredView(source, R.id.weixin_login, "method 'weixin_login'");
    view2131230916 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.weixin_login();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view2131230841.setOnClickListener(null);
    view2131230841 = null;
    view2131230916.setOnClickListener(null);
    view2131230916 = null;
  }
}
