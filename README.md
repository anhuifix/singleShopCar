### singleShopCar
   购物车项目，业务需要实现了一个购物车的项目，简单的了解下实现逻辑：数据计算等是在Adapter中计算出来的，通过在Adapter中计算出来的数据就可以回调到Activity中进行订单操作等功能业务逻辑，每一个店铺产生的数据是走一条流程的，（业务需求：不是作为一个类似淘宝，京东的平台数据又由平台分发，所以我们实现的是一对一的客户交易的交易流程)接着往下看：

1. **界面使用到的控件**

  					goodsAdapter = new GoodsCarAdapter(ShopCarAvtivity.this, result);
                    lv_refresh.setAdapter(goodsAdapter);
                    goodsAdapter.setCheckInterface(ShopCarAvtivity.this);// 关键步骤1,设置复选框接口
                    goodsAdapter.setModifyCountInterface(ShopCarAvtivity.this);// 关键步骤2,设置数量增减接口
                    for (int i = 0; i < goodsAdapter.getGroupCount(); i++) {
                        lv_refresh.expandGroup(i);// 关键步骤3,初始化时，将ExpandableListView以展开的方式呈现
                    }
2.**项目中使用到的数据接口**
	
	界面当中的复选框的接口回调

	    public interface CheckGoodsListener {
	    /**
	     * 组选框状态改变触发的事件
	     *
	     * @param groupPosition 组元素位置
	     * @param isChecked     组元素选中与否
	     */
	    void checkGroup(int groupPosition, boolean isChecked);
	
	    /**
	     * 子选框状态改变时触发的事件
	     *
	     * @param groupPosition 组元素位置
	     * @param childPosition 子元素位置
	     * @param isChecked     子元素选中与否
	     */
	    void checkChild(int groupPosition, int childPosition, boolean isChecked);
	
	
	    /**
	     * 购买
	     * @param groupPosition
	     * @param childPosition
	     * @param isChecked
	     */
	    void checkGoodsBuy(ShopCarModel shopCarModel, double totalMonery, int totalCount);

		}	 

    商品增加和修改的接口

		/**
		 * 改变数量的接口
		 * Created by zhuangAH on 2016-11-7.
		 */
		
		public interface ModifyCountListener {
		
		    /**
		     * 增加操作
		     *
		     * @param groupPosition 组元素位置
		     * @param childPosition 子元素位置
		     * @param showCountView 用于展示变化后数量的View
		     * @param isChecked     子元素选中与否
		     */
		    void doIncrease(int groupPosition, int childPosition, View showCountView, boolean isChecked);
		
		    /**
		     * 删减操作
		     *
		     * @param groupPosition 组元素位置
		     * @param childPosition 子元素位置
		     * @param showCountView 用于展示变化后数量的View
		     * @param isChecked     子元素选中与否
		     */
		    void doDecrease(int groupPosition, int childPosition, View showCountView, boolean isChecked);
		
		    /**
		     * 删除子item
		     *
		     * @param groupPosition
		     * @param childPosition
		     */
		    void childDelete(int groupPosition, int childPosition);
		
		}
		  
3.**在Adapter中计算商品的金额数量**

   单个店铺中有多个商品，所以这个店铺的布局，包含选择全组的按钮，使用了接口的回调 checkInterface.checkGroup(groupPosition, ((CheckBox) v).isChecked());来判断是否选中全组，在Activity中进行数据的便利是否选中商品之后再刷新数据。

	    @Override
	    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
	
	        GroupViewHolder gholder = null;
	        if (convertView != null && !(convertView.getTag() instanceof GroupViewHolder)) {
	            convertView = null;
	        }
	        if (convertView == null) {
	            gholder = new GroupViewHolder();
	            convertView = View.inflate(context, R.layout.item_shopcart_group, null);
	            gholder.determineChekbox = (CheckBox) convertView.findViewById(R.id.determine_chekbox);
	            gholder.tvSourceName = (TextView) convertView.findViewById(R.id.tv_source_name);
	            convertView.setTag(gholder);
	        } else {
	            gholder = (GroupViewHolder) convertView.getTag();
	        }
	
	        final ShopCarModel group = (ShopCarModel) getGroup(groupPosition);
	        gholder.tvSourceName.setText(group.getFactoryName());
	        gholder.determineChekbox.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                group.setChoosed(((CheckBox) v).isChecked());
	                checkInterface.checkGroup(groupPosition, ((CheckBox) v).isChecked());
	            }
	        });
	        gholder.determineChekbox.setChecked(group.isChoosed());
	        return convertView;
	    }


   在Activity中计算便利数据

	 /***
	     * 校对组元素
	     *
	     * @param groupPosition 组元素位置
	     * @param isChecked     组元素选中与否
	     */
	    @Override
	    public void checkGroup(int groupPosition, boolean isChecked) {
	
	        List<GoodsModel> goodsModelList = shopCarModelList.get(groupPosition).getGoodsModel();
	        for (int i = 0; i < goodsModelList.size(); i++) {
	            goodsModelList.get(i).setChoosed(isChecked);
	        }
	        goodsAdapter.notifyDataSetChanged();
	    }


   在商品中计算数据结果：



 	@Override
    public View getChildView(final int groupPosition, final int childPosition, final boolean isLastChild, View convertView, final ViewGroup parent) {
        GoodsViewHolder goodsViewHolder = null;
        int totalCount = 0;
        double totalPrice = 0.00;

        if (convertView != null && !(convertView.getTag() instanceof GoodsViewHolder)) {
            convertView = null;
        }
        if (convertView == null) {
            goodsViewHolder = new GoodsViewHolder();
            convertView = View.inflate(context, R.layout.item_shopcart_product, null);
            goodsViewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.check_box);
            goodsViewHolder.ivAdapterListPic = (ImageView) convertView.findViewById(R.id.iv_adapter_list_pic);
            goodsViewHolder.tvIntro = (TextView) convertView.findViewById(R.id.tv_intro);
            goodsViewHolder.tvPrice = (TextView) convertView.findViewById(R.id.tv_price);
            goodsViewHolder.tvBuyNum = (TextView) convertView.findViewById(R.id.tv_buy_num);
            goodsViewHolder.rlNoEdtor = (RelativeLayout) convertView.findViewById(R.id.rl_no_edtor);
            goodsViewHolder.tvReduce = (TextView) convertView.findViewById(R.id.tv_reduce);
            goodsViewHolder.tvNum = (TextView) convertView.findViewById(R.id.tv_num);
            goodsViewHolder.tvAdd = (TextView) convertView.findViewById(R.id.tv_add);
            goodsViewHolder.llChangeNum = (LinearLayout) convertView.findViewById(R.id.ll_change_num);
            goodsViewHolder.layout_item_foot = (LinearLayout) convertView.findViewById(R.id.layout_item_foot);
            goodsViewHolder.tv_goods_number = (TextView) convertView.findViewById(R.id.tv_goods_number);
            goodsViewHolder.tv_goods_menoy = (TextView) convertView.findViewById(R.id.tv_goods_menoy);
            goodsViewHolder.tv_buys = (TextView) convertView.findViewById(R.id.tv_buys);
            goodsViewHolder.laytou_car = (LinearLayout) convertView.findViewById(R.id.laytou_car);
            convertView.setTag(goodsViewHolder);
        } else {
            goodsViewHolder = (GoodsViewHolder) convertView.getTag();
        }
        //进行数据操作
        final GoodsModel goodsInfo = (GoodsModel) getChild(groupPosition, childPosition);
        if (goodsInfo != null) {
            //数量初始化为0,金额初始化为0
            goodsViewHolder.tv_goods_number.setText(TypeUtils.toString(0));
            goodsViewHolder.tv_goods_menoy.setText("￥ " + NumberUtils.formatMoneyScale(0.00));

            List<GoodsModel> goodsModel = CarUtrils.getGoodsList(goodShop, groupPosition);
            //判断是否最后一个
            if ((goodsModel.size() - 1) == childPosition) {
                goodsViewHolder.layout_item_foot.setVisibility(View.VISIBLE);
                /**
                 * 统计操作<br>
                 * 1.先清空全局计数器<br>
                 * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作<br>
                 * 3.给底部的textView进行数据填充
                 */
                //1判断商品是否选中,再进行计算
                for (int j = 0; j < goodsModel.size(); j++) {
                    GoodsModel model = goodsModel.get(j);
                    if (model.isChoosed()) {
                        totalCount += model.getSelectQty();
                        totalPrice += TypeUtils.toDouble(NumberUtils.multiply(TypeUtils.toBigDecimal(NumberUtils.formatRounded(model.getPrice())), NumberUtils.toBigDecimal(model.getSelectQty())));
                    }
                }

                if (totalPrice != 0.00) {
                    goodsViewHolder.tv_goods_number.setText(TypeUtils.toString(totalCount));
                    goodsViewHolder.tv_goods_menoy.setText(NumberUtils.formatRounded(TypeUtils.toBigDecimal(totalPrice)));
                    goodsViewHolder.tv_buys.setBackgroundColor(context.getResources().getColor(R.color.main_color));
                    goodsViewHolder.tv_buys.setEnabled(true);
                } else {
                    goodsViewHolder.tv_goods_number.setText(TypeUtils.toString(0));
                    goodsViewHolder.tv_goods_menoy.setText("￥ " + NumberUtils.formatMoneyScale(0.00));
                    goodsViewHolder.tv_buys.setBackgroundColor(context.getResources().getColor(R.color.resport_line));
                    goodsViewHolder.tv_buys.setEnabled(false);
                }

            } else {
                goodsViewHolder.layout_item_foot.setVisibility(View.GONE);
            }
            //设置基础数据
            if (goodsInfo.getImageSrc() != null) {
                Glide.with(context)
                        .load(goodsInfo.getImageSrc())
                        .centerCrop()
                        .placeholder(R.mipmap.test2)
                        .crossFade()
                        .into(goodsViewHolder.ivAdapterListPic);
            }

            goodsViewHolder.tvIntro.setText(goodsInfo.getName());
            goodsViewHolder.tvPrice.setText("￥ " + NumberUtils.formatRounded(goodsInfo.getPrice()));
            goodsViewHolder.tvBuyNum.setText("X " + NumberUtils.formatQty(goodsInfo.getQty()));
            //set Goods Check
            goodsViewHolder.checkBox.setChecked(goodsInfo.isChoosed());
            goodsViewHolder.tvNum.setText(TypeUtils.toString(goodsInfo.getSelectQty()));

            //选中的状态下才能触发点击事件
            goodsViewHolder.tvAdd.setEnabled(true);
            goodsViewHolder.tvReduce.setEnabled(true);
            //加减
            final GoodsViewHolder finalGoodsViewHolder = goodsViewHolder;
            goodsViewHolder.tvAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    modifyCountInterface.doIncrease(groupPosition, childPosition, finalGoodsViewHolder.tvNum, finalGoodsViewHolder.checkBox.isChecked());// 暴露增加接口

                }
            });
            final GoodsViewHolder finalGoodsViewHolder1 = goodsViewHolder;
            goodsViewHolder.tvReduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    modifyCountInterface.doDecrease(groupPosition, childPosition, finalGoodsViewHolder1.tvNum, finalGoodsViewHolder1.checkBox.isChecked());// 暴露删减接口
                }
            });

            //goods check state OnClick
            goodsViewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goodsInfo.setChoosed(((CheckBox) v).isChecked());
                    ((CheckBox) v).setChecked(((CheckBox) v).isChecked());
                    checkInterface.checkChild(groupPosition, childPosition, ((CheckBox) v).isChecked());
                }
            });
            //onClick to OrderDetailActivity
            final double finalTotalPrice = totalPrice;
            final int finalTotalCount = totalCount;
            goodsViewHolder.tv_buys.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //check goods is not null
                    //if not null can go other activity
                    ShopCarModel shopCar = CarUtrils.getCheckShopCar(goodShop, groupPosition);
                    if (shopCar != null && shopCar.getGoodsModel().size() > 0) {
                        checkInterface.checkGoodsBuy(shopCar, finalTotalPrice, finalTotalCount);
                    }
                }
            });
        }

        goodsViewHolder.laytou_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               UIHelper.toShopCarGoodsDetailActivity(context, goodsInfo);
            }
        });
        return convertView;
    }


   以上代码的是先判断当前组是否为一个店铺中的最后一个，在最后一个元素中局部计算当前的Group的数据，根据数据来选择是否复位数据展示以及显示，把计算的数据展示出来，最后通过接口回调的方式跳转界面，把数据传到Activity中去。就这样子就处理完这个购物车的逻辑，以上可能不符合你的逻辑，但是你可以稍微修改就拿来使用。
