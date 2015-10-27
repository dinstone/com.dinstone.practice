
package com.dinstone.practice.redblue;

/**
 * @category 听到有人忽悠说双色球二等奖比三等奖好中,还说打电话去福彩中心,人说"Yes!" 很愤慨 这国家到处都是忽悠只缘于你们高中数学真的不及格
 *           例如 我们国家福彩中心规则玩法 : 33个红球里选6,16个蓝球里选1 忽悠: " 2等奖几率大于3等奖 " 事实上这里有一个陷阱
 *           二等奖的算法 应该是涵盖15个未摇中的篮球 因为不这样做就可能涵盖一等奖了
 *           [c15(1)*(1/[start2generateBalls])] 三等奖的算法应该是 6球选5球 再剩下的27球里选一个球
 *           这样就不会涵盖二等奖 c6(5)*c27(1)*(1/[start2generateBalls])
 * @author Stig.DK Dim at 上午7:11:26
 */
public class LotteryQuestion {

    private static int start2generateBalls() {
        return C(33, 6) * C(16, 1);// 双色球33选6 16选1
    }

    static int C(int k, int n) {
        int rs = 1;
        for (int i = 1; i <= n; i++)
            rs = rs * (k - i + 1) / i;
        return rs;
    }

    static int Bingo(int type) {
        int fenmu;
        switch (type) {
        case 1:
            fenmu = 1; // 6+1 1/c33(6)*c16(1)
            break;
        case 2:
            fenmu = 15; // 6+0 c15(1)/c33(6)*c16(1)
            break;
        case 3:
            fenmu = 6 * 27; // 5+1 c6(5)*c27(1)/c33(6)*c16(1)
            break;
        case 4:
            fenmu = C(6, 5) * C(27, 1) * C(15, 1) + C(6, 4) * C(27, 2); // 5+0 &
                                                                        // 4+1
            // (c6(5)*c27(1)*c15(1)+c6(4)*c27(2))/c33(6)*c16(1)
            break;
        case 5:
            fenmu = C(6, 4) * C(27, 2) * C(15, 1) + C(6, 3) * C(27, 3); // 4+0 &
                                                                        // 3+1
                                                                        // (c6(4)*c27(2)*c15(1)+c6(3)*c27(3))/c33(6)*c16(1)
            break;
        case 6:
            fenmu = C(6, 2) * C(27, 4) + C(6, 1) * C(27, 5) + C(27, 6);
            break;
        default:
            fenmu = 0;
            break;
        }
        return start2generateBalls() / fenmu;
    }

    public static void main(String[] args) {
        System.out.println("[双色球一等奖中奖几率] -> [1/" + Bingo(1) + "]\n亲,500w!");
        System.out.println("[双色球二奖中奖几率] -> [2/" + Bingo(2) + "]\n亲,166w!");
        System.out.println("[双色球三等奖中奖几率]->  [1/" + Bingo(3) + "]\n亲,3000元");
        System.out.println("[双色球四等奖中奖几率]->  [1/" + Bingo(4) + "]\n亲,没中过");
        System.out.println("[双色球五等奖中奖几率]->  [1/" + Bingo(5) + "]\n亲,求安慰");
        System.out.println("[双色球末等奖中奖几率]->  [1/" + Bingo(6) + "]\n亲,月月都来一次!");
    }

}// end class