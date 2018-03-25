/**
 * Created by Administrator on 2017-05-06.
 */
public enum MajiangPai {

    TIAO1("1条", "t1", 0),
    TIAO2("2条", "t2", 1),
    TIAO3("3条", "t3", 2),
    TIAO4("4条", "t4", 3),
    TIAO5("5条", "t5", 4),
    TIAO6("6条", "t6", 5),
    TIAO7("7条", "t7", 6),
    TIAO8("8条", "t8", 7),
    TIAO9("9条", "t9", 8),

    TONG1("1筒", "o1", 9),
    TONG2("2筒", "o2", 10),
    TONG3("3筒", "o3", 11),
    TONG4("4筒", "o4", 12),
    TONG5("5筒", "o5", 13),
    TONG6("6筒", "o6", 14),
    TONG7("7筒", "o7", 15),
    TONG8("8筒", "o8", 16),
    TONG9("9筒", "o9", 17),

    WAN1("1万", "w1", 18),
    WAN2("2万", "w2", 19),
    WAN3("3万", "w3", 20),
    WAN4("4万", "w4", 21),
    WAN5("5万", "w5", 22),
    WAN6("6万", "w6", 23),
    WAN7("7万", "w7", 24),
    WAN8("8万", "w8", 25),
    WAN9("9万", "w9", 26),

    ZHONG("中", "z", 27),
    FA("發", "f", 28),
    BAI("白", "b", 29),

    DONG("东", "e", 30),
    NAN("南", "s", 31),
    XI("西", "x", 32),
    BEI("北", "n", 33),

    CHUN("春", "c", 34),
    XIA("夏", "a", 35),
    QIU("秋", "q", 36),
    DON("冬", "d", 37),

    MEI("梅", "m", 38),
    LAN("兰", "l", 39),
    ZHU("竹", "u", 40),
    JU("菊", "j", 41),


    LAIZI("癞子",  "y",  60);   //  癞子


    private String name;
    private String code;
    private int index;

    private MajiangPai(String name, String code, int index) {
        this.name = name;
        this.code = code;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
