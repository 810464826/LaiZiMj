import java.util.ArrayList;
import java.util.List;
/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        //这是金牌（癞子）
        MajiangPai laiz = MajiangPai.XI;

        //这是手牌
        MajiangPai[] hCards1 = new MajiangPai[]{
                MajiangPai.XI, MajiangPai.WAN4, MajiangPai.WAN4,
                MajiangPai.WAN1, MajiangPai.WAN2, MajiangPai.WAN3,
                MajiangPai.WAN5, MajiangPai.WAN6, MajiangPai.WAN7,
                MajiangPai.TIAO7, MajiangPai.TIAO7,
                MajiangPai.TONG1, MajiangPai.TONG1,
                MajiangPai.DONG, MajiangPai.DONG, MajiangPai.DONG};

        //这是手牌
        MajiangPai[] hCards2 = new MajiangPai[]{
                MajiangPai.WAN4, MajiangPai.WAN4, MajiangPai.WAN4,
                MajiangPai.WAN1, MajiangPai.WAN2, MajiangPai.WAN3,
                MajiangPai.WAN5, MajiangPai.WAN6, MajiangPai.WAN7,
                MajiangPai.TIAO7, MajiangPai.TIAO7,
                MajiangPai.TONG1, MajiangPai.TONG1,
                MajiangPai.DONG, MajiangPai.DONG, MajiangPai.DONG};

        //这是手牌
        MajiangPai[] hCards3 = new MajiangPai[]{
                MajiangPai.WAN4, MajiangPai.WAN4, MajiangPai.WAN4,
                MajiangPai.WAN1, MajiangPai.WAN2, MajiangPai.WAN3,
                MajiangPai.WAN6, MajiangPai.WAN6, MajiangPai.WAN7,
                MajiangPai.TIAO7, MajiangPai.TIAO7,
                MajiangPai.TONG1, MajiangPai.TONG1,
                MajiangPai.DONG, MajiangPai.DONG, MajiangPai.DONG};

        List<MajiangPai> parameter = new ArrayList<MajiangPai>();

        //这里将金牌转为癞子
        for (MajiangPai m : hCards3) {
            if (m == laiz) {
                parameter.add(MajiangPai.LAIZI);
            } else {
                parameter.add(m);
            }
        }

        Majiao m1 = new Majiao(parameter);
        m1.sort();
        System.out.println("原始牌: " + m1.toString());

        //这里对金牌组合求解 得到答案
        for (Result r : m1.solve()) {
            System.out.println(r.toString());
        }
    }
}


