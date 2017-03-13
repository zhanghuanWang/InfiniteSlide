# InfiniteSlide
提供简单的滑动变换，支持横竖模式，滑动变换自定义

![](http://upload-images.jianshu.io/upload_images/2440045-9209cc458fde6ea0.gif?imageMogr2/auto-orient/strip)	
![](http://upload-images.jianshu.io/upload_images/2440045-33b2eb24d2637850.gif?imageMogr2/auto-orient/strip)

# Usage
1,download module and dependent(下载并且依赖到项目中)
  
2,config in xml(在xml中定义控件)
```
<com.david.infiniteslidelib.view.InfiniteSlideView
        android:id="@+id/slide_view1"
        android:layout_width="200dp"
        android:layout_height="match_parent"
        android:layout_alignParentLeft="true"
        app:view_orientation="vertical"></com.david.infiniteslidelib.view.InfiniteSlideView>;
```
              
参数 view_orientation有两个值vertical和horizontal，vertical表示控件是竖直滑动，horizontal表示控件横向滑动。默认是horizontal

也可以通过java代码设置
 ```
   setView_orientation(InfiniteSlideView.SLIDE_MODE_VERTICAL);
 ```
 
 3，定义adapter，需要继承SlideBaseAdapter.像平时定义RecyclerView一样定义，只是原来getItemCount()改为getRealCount()用法一样
     ```
     class MyAdapter extends SlideBaseAdapter<MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // like RecyclerView
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            // like RecyclerView
           
        }

        @Override
        public int getRealCount() {
             // like RecyclerView getItemCount()
        }
    }
     ```
     
 4，设置adapter
  ```
    myInfiniteSlideView.setAdapter(MyAdapter);
    ```
    
 5,下面是最激动人心的时刻了。继承接口自定义自己想要的动画
    ```
    myInfiniteSlideView.setSlideViewListener(new SlideViewListener() {
            @Override
            public void onChange(View v, float offset, boolean positive) {
                v.setScaleX(1 - offset * 0.5f);
                v.setScaleY(1 - offset * 0.5f);
            }
        });
    ```
    这里有三个参数 :<p>
      (1)View v 表示需要转换的view，即当前需要定义的View本身<p>
      (2)float offset 表示view相对中心点的偏移量.这个值在1和0之间变动。0表示这个view在父容器的中心，1表示这个view在最旁边<p>
      (3)boolean position 表示当前view所处的位置<p>

  note:示例代码表示的是图片中的效果
    
    
      
    
  
