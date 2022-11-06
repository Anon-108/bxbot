package com.binance.client.model.enums;

import com.binance.client.impl.utils.EnumLookup;

/**
 * The event that Asset Change Notification Related ,for example : create order (order.place) ,
  commit order (order.match),order refunds（order.refund),order canceled (order.cancel) ,card
  deducts transaction fee （order.fee-refund),lever account transfer（margin.transfer),loan
  principal（margin.loan),loan interest （margin.interest),return loan interest(margin.repay),other
  asset change(other)
 *与资产变更通知相关的事件，例如：创建订单（order.place），
 *    提交订单（order.match），订单退款（order.refund），订单取消（order.cancel），卡
 *    扣除交易手续费（order.fee-refund），杠杆账户转账（margin.transfer），贷款
 *    本金（margin.loan），贷款利息（margin.interest），还贷利息（margin.repay），其他
 *    资产变动（其他）
 */
public enum AccountChangeType {

  /**
   *  新委托单量
   */
  NEWORDER("order.place"),
  /**
   * 交易
   */
  TRADE("order.match"),
  /**
   * 退款
   */
  REFUND("order.refund"),
  /**
   * 取消订单
   */
  CANCELORDER("order.cancel"),
  /**
   * 订单费退款
   */
  FEE("order.fee-refund"),
  /**
   * 保证金转移
   */
  TRANSFER("margin.transfer"),
  /**
   * 保证金贷款
   */
  LOAN("margin.loan"),
  /**
   * 保证金利息
   */
  INTEREST("margin.interest"),
  /**
   * 保证金偿还
   */
  REPAY("margin.repay"),
  /**
   * 其它
   */
  OTHER("other"),
  /**
   * 无效
   */
  INVALID("INVALID");

  private final String code;

  /**
   * 账户变更类型
   * @param code
   */
  AccountChangeType(String code) {
    this.code = code;
  }

  @Override
  public String toString() {
    return code;
  }

  /**
   * 查找
   */
  private static final EnumLookup<AccountChangeType> lookup = new EnumLookup<>(
      AccountChangeType.class);

  /**
   * 查找
   * @param name
   * @return
   */
  public static AccountChangeType lookup(String name) {
    return lookup.lookup(name);
  }


}
