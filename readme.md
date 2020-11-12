## DruidMall
### description
> 这个项目是平常自己做测试用的
>

### 设计一个页面，通过弹出框进行增删改查
#### 设计数据库
> 

### 餐厅点餐排队系统
> 1.用户模块
> 2.点餐模块
> 3.订单模块
> 4.支付模块
> 5.商品展示模块
> 6.购物车模块
>

#### 用户模块
> 在主页面独立用户模块的一个导航栏，点击该导航栏，进入用户管理界面
> 显示所有用户的信息，并可以做增删改查(批量)等操作
> 记录用户最近一次的访问时间(这个需要用户登录才能获取到，但是一般选购的时候不需要登录，只有将菜品加入购物车才会提示登录)
> 

#### 点餐模块
> 用户进入点餐界面，显示所有的菜品信息，有折扣的在最上面显示，然后依次分类别显示，用户点击加入购物车，先判断用户是否登录，
> 如果未登录，则进行登录，已登录则加入购物车(这个菜品需要写入到缓存中，等生成订单再写入数据库，因为用户可能频繁的
> 加减购物车中的商品)，继续进行选购

#### 订单模块
>

#### 支付模块
> 

#### 菜品展示模块
>

#### 购物车模块
> 

#### 流程
> 用户扫码进入 OrderSystem，然后进入主页面，主页面介绍餐厅(主要是环境，特色等)，中央位置有一个预订连接，点击跳转预订界面
> 预订界面显示店内一些菜品信息，以及折扣
> 选择套餐，座位号，到店时间(用户晚到怎么办)，点击预订，到预订时间后，会有短信通知，如果用户五分钟内不来，预订座位将被释放
> 用户进店用餐后，则改变预订订单的状态，用户离店则设置该座位号为可预订(不离店也可以预订)(预订的时候可以选择座位号，没有座位号也可以预订)
> 
>
>
#### 数据库设计 ordersystem
>  user(用户表，会员)
>   user_id username password nickname birth member phone gender is_admin pwd_reset_time create_time update_time
>   is_admin 1 true 0 false  member 1 true 0 false
>  menu(菜品表)
>   menu_id name money image sales(销量)
>  combo(套餐)
>   combo_id menu_id coupon_id coupon_money total_money
>  seat(座位表)
>   seat_id people_num seat_name seat_status
>   seat_status 1 有人 0 没人
>  order(订单表)
>   order_id user_id seat_id(one or null) create_time order_status total_money 
>   order_status 1 订单完成 0 订单未完成
>  order_menu(中间表)
>   order_id menu_id
>  combo_menu(中间表)
>   combo_id menu_id
>  coupon(优惠券)
>   coupon_id coupon description
>  introduce(商家介绍)
>   introduce_id mall_name description image location phone 
>  menu_top(菜单推荐)
>   menu_top_id menu_id coupon_money total_money
>  message(留言)
>   message_id user_id menu_id context create_time status
>   status 1 未删除 0 删除


#### 问题
> 原型图没有画好，设计不合理
> 需求理清楚再写代码