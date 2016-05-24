/*
 * Copyright 2016 "Henry Tao <hi@henrytao.me>"
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.henrytao.smoothappbarlayout.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by henrytao on 5/17/16.
 */
public class DispatcherView extends FrameLayout {

  public DispatcherView(Context context) {
    super(context);
  }

  public DispatcherView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public DispatcherView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public DispatcherView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override
  public boolean dispatchTouchEvent(MotionEvent ev) {
    if (ev.getAction() == MotionEvent.ACTION_MOVE) {
      //if move event occurs
      long downTime = SystemClock.uptimeMillis();
      long eventTime = SystemClock.uptimeMillis() + 100;
      float x = ev.getRawX();
      float y = ev.getRawY();
      int metaState = 0;
      //create new motion event
      MotionEvent motionEvent = MotionEvent.obtain(
          downTime,
          eventTime,
          MotionEvent.ACTION_DOWN,
          x,
          y,
          metaState
      );
      //I store a reference to listview in this layout
      getParent().dispatchTouchEvent(motionEvent); //send event to listview
      return true;
    }
    return super.dispatchTouchEvent(ev);
  }


}
