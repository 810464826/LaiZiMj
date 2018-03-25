import java.util.List;
/**
 * Created by Administrator on 2017-05-06.
 */
public class Result {
    private List<MajiangPai>  cards ;

    private List<MajiangPai>  hps;

    public List<MajiangPai> getCards() {
        return cards;
    }

    public void setCards(List<MajiangPai> cards) {
        this.cards = cards;
    }

    public List<MajiangPai> getHps() {
        return hps;
    }

    public void setHps(List<MajiangPai> hps) {
        this.hps = hps;
    }

    public Result(List<MajiangPai> cards, List<MajiangPai> hps) {
        this.cards = cards;
        this.hps = hps;
    }

    @Override
    public String toString() {

        String msg = "";
        for (MajiangPai p : cards) {
            msg += p.getName() + "  ,";
        }

        msg  +=  "   和牌 ：";
        for (MajiangPai  rp :  hps){
            msg += rp.getName() + "  ,";
        }
        return msg;
    }
}
