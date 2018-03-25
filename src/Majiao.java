import java.util.*;

/**
 * 0  1  2  3  4  5  6  7  8   9  10 11 12 13 14 15 16 17  18 19 20 21 22 23 24 25 26  27 28 29 30 31 32 33 34 35 36 37 38 39 40 41
 * t1 t2 t3 t4 t5 t6 t7 t8 t9  o1 o2 o3 o4 o5 o6 o7 o8 o9  w1 w2 w3 w4 w5 w6 w7 w8 w9  z  f  b  e  s  x  n  c  a  q  d  m  l  u  j
 * 1条 2条  3条  4条  5条 6条 7条 8条 9条    1筒 2筒  3筒 4筒  5筒  6筒 7筒  8筒 9筒   1万  2万 3万  4万 5万  6万 7万  8万 9万    中    发    白    东    南    西    北    春    夏    秋    冬   梅     兰    竹    菊
 *
 * @author 小石潭记 这是判断胡的方法 吃 /碰 /杠（特殊杠）
 *         <p/>
 *         2017年4月13日 上午11:19:44
 */
public class Majiao {

    private List<MajiangPai> cards = new ArrayList<MajiangPai>();   //  非癞子牌

    private int jingCount = 0;   //  金牌数量
    Set<MajiangPai> hasConfirmPs = new HashSet<MajiangPai>();   //  当前手上的牌种类

    //构造函数
    public Majiao(List<MajiangPai> cards) {
        List<String> strs = new ArrayList<String>();
        for (MajiangPai p : cards) {
            if (p != MajiangPai.LAIZI) {
                this.cards.add(p);
                strs.add(p.getCode());
                hasConfirmPs.add(p);
            } else {
                this.jingCount++;
            }
        }
    }


    @Override
    public String toString() {
        String msg = "";
        for (MajiangPai p : cards) {
            msg += p.getName() + "  ,";
        }
        for (int i = 0; i < jingCount; i++) {
            msg += MajiangPai.LAIZI.getName() + " ,";
        }
        return msg;
    }

    //对手上的牌进行排序
    public void sort() {
        Collections.sort(this.cards, new Comparator<MajiangPai>() {
            public int compare(MajiangPai p1, MajiangPai p2) {
                return p1.getIndex() - p2.getIndex();
            }
        });

    }

    /**
     * 核心求解方法
     * @return
     */
    public List<Result> solve() {
        return new ArrayList<Result>() {{
            for (List<MajiangPai> e : combine()) {
                List<MajiangPai> hf = checkNormalTing(hCards2Array(e), e.size());
                if (hf != null && hf.size() > 0) {
                    add(new Result(e, hf));
                }
            }
        }};
    }


    /**
     * 求癞子组合排
     *
     * @return
     */
    private List<List<MajiangPai>> combine() {
        if (this.jingCount == 0) {
            return new ArrayList<List<MajiangPai>>() {{
                add(cards);
            }};
        } else {
            List<List<MajiangPai>> results = new ArrayList<List<MajiangPai>>();
            for (int i = 0; i < jingCount; i++) {
                if (i == 0) {
                    results.addAll(descartes(this.cards, hasConfirmPs));
                } else {
                    List<List<MajiangPai>> source = new ArrayList<List<MajiangPai>>();
                    source.addAll(results);
                    results.clear();
                    for (List<MajiangPai> e : source) {
                        results.addAll(descartes(e, hasConfirmPs));
                    }
                }
            }
            return results;
        }
    }

    /**
     * 求笛卡尔组合
     * @param source1  目标组合
     * @param source2  癞子可以充当的牌
     * @return
     */
    private List<List<MajiangPai>> descartes(List<MajiangPai> source1, Set<MajiangPai> source2) {
        List<List<MajiangPai>> r = new ArrayList<List<MajiangPai>>();
        for (MajiangPai card : source2) {
            List<MajiangPai> ps = new ArrayList<MajiangPai>();
            ps.addAll(source1);
            ps.add(card);
            r.add(ps);
        }
        return r;

    }

    /**
     * 求某一个组合具体的解
     * @param count
     * @param size
     * @return
     */
    private List<MajiangPai> checkNormalTing(int[] count, int size) {
        int[] tmp = new int[42];
        List<MajiangPai> huPai = new ArrayList<MajiangPai>();   //  可胡的怕
        for (int i = 0; i < 42; i++) {
            count[i]++;
            for (int j = 0; j < 42; j++) {
                tmp[j] = count[j];
            }
            if (isHU(tmp, size)) {
                huPai.add(translate(i));
            }
            count[i]--;
        }
        return huPai;
    }

    /**
     * 将手牌转为数组
     * @param hCards
     * @return
     */
    private int[] hCards2Array(List<MajiangPai> hCards) {
        List<String> tiao = new ArrayList<String>();
        List<String> tong = new ArrayList<String>();
        List<String> wan = new ArrayList<String>();
        int[] count = new int[42];
        for (int i = 0; i < 42; i++) {
            count[i] = 0;
        }

        for (MajiangPai p : hCards) {

            String code = p.getCode();

            if (code.charAt(0) == 't') {
                int num = Integer.parseInt(code.charAt(1) + "") - 1;
                tiao.add("t" + code.charAt(1));
                count[num]++;
            } else if (code.charAt(0) == 'o') {
                int num = Integer.parseInt(code.charAt(1) + "") - 1;
                tong.add("o" + code.charAt(1));
                count[num + 9]++;
            } else if (code.charAt(0) == 'w') {
                int num = Integer.parseInt(code.charAt(1) + "") - 1;
                wan.add("w" + code.charAt(1));
                count[num + 9 * 2]++;
            } else if (code.charAt(0) == 'z') {
                count[27]++;
            } else if (code.charAt(0) == 'f') {
                count[28]++;
            } else if (code.charAt(0) == 'b') {
                count[29]++;
            } else if (code.charAt(0) == 'e') {
                count[30]++;
            } else if (code.charAt(0) == 's') {
                count[31]++;
            } else if (code.charAt(0) == 'x') {
                count[32]++;
            } else if (code.charAt(0) == 'n') {
                count[33]++;
            } else if (code.charAt(0) == 'c') {
                count[34]++;
            } else if (code.charAt(0) == 'a') {
                count[35]++;
            } else if (code.charAt(0) == 'q') {
                count[36]++;
            } else if (code.charAt(0) == 'd') {
                count[37]++;
            } else if (code.charAt(0) == 'm') {
                count[38]++;
            } else if (code.charAt(0) == 'l') {
                count[39]++;
            } else if (code.charAt(0) == 'u') {
                count[40]++;
            } else if (code.charAt(0) == 'j') {
                count[41]++;
            }
        }
        return count;
    }


    /**
     * 这是将单张牌转为30位长度的数组
     */
    public int[] card2Array(String card) {
        List<String> tiao = new ArrayList<String>();
        List<String> tong = new ArrayList<String>();
        List<String> wan = new ArrayList<String>();
        // TDW排列 这是存放 条 筒 万 按顺序存放的1-9的个数
        int[] count = new int[42];

        // 首先将每个牌的个数置为0
        for (int i = 0; i < 42; i++) {
            count[i] = 0;
        }
        // 循环手牌 计算每个牌的个数
        if (card.charAt(0) == 't') {
            int num = Integer.parseInt(card.charAt(1) + "") - 1;
            tiao.add("t" + card.charAt(1));
            count[num]++;
        } else if (card.charAt(0) == 'o') {
            // 筒
            int num = Integer.parseInt(card.charAt(1) + "") - 1;
            tong.add("o" + card.charAt(1));
            // 因为是按 条 筒 万 的顺序 所以筒的下标要加9
            count[num + 9]++;
        } else if (card.charAt(0) == 'w') {
            // 万
            int num = Integer.parseInt(card.charAt(1) + "") - 1;
            wan.add("w" + card.charAt(1));
            // 因为是按 条 筒 万 的顺序 所以筒的下标要加9*2
            count[num + 9 * 2]++;
        } else if (card.charAt(0) == 'z') {
            // 红中 28
            count[27]++;
        } else if (card.charAt(0) == 'f') {
            // 发财 29
            count[28]++;
        } else if (card.charAt(0) == 'b') {
            // 白板 30
            count[29]++;
        } else if (card.charAt(0) == 'e') {
            // 东风
            count[30]++;
        } else if (card.charAt(0) == 's') {
            // 南风
            count[31]++;
        } else if (card.charAt(0) == 'x') {
            // 西风
            count[32]++;
        } else if (card.charAt(0) == 'n') {
            // 北风
            count[33]++;
        } else if (card.charAt(0) == 'c') {
            // 春
            count[34]++;
        } else if (card.charAt(0) == 'a') {
            // 夏
            count[35]++;
        } else if (card.charAt(0) == 'q') {
            // 秋
            count[36]++;
        } else if (card.charAt(0) == 'd') {
            // 冬
            count[37]++;
        } else if (card.charAt(0) == 'm') {
            // 梅
            count[38]++;
        } else if (card.charAt(0) == 'l') {
            // 兰
            count[39]++;
        } else if (card.charAt(0) == 'u') {
            // 竹
            count[40]++;
        } else if (card.charAt(0) == 'j') {
            // 菊
            count[41]++;
        }
        return count;
    }


    /**
     *
     * @param i  通过牌的编码返回具体的牌
     * @return
     */
    public MajiangPai translate(int i) {

        for (MajiangPai p : MajiangPai.values()) {
            if (p.getIndex() == i) {
                return p;
            }
        }
        return null;

    }

    /**
     * 通过传入上边处理排好序的数组count去判断是否能否胡牌
     *
     * @param count
     * @param hCdlength 这是传的手牌的长度 原来的是14
     * @return
     */
    public boolean isHU(int[] count, int hCdlength) {
        boolean result = tryHU(count, hCdlength + 1);
        return result;
    }

    /**
     * 这是真正处理业务逻辑的 判断是否能胡牌的
     *
     * @param count 上边处理的数组count
     * @param len   传入的牌的长度
     */
    public boolean tryHU(int[] count, int len) {
        // 如果牌的长度为0时，则返回true 代表可以胡牌了
        // 这是判断不能缺门
        if (len == 0) {
            return true;
        }
        // 对子 len % 3 == 2 这是判断是否有对子的情况
        if (len % 3 == 2) {
            // count=[0, 0, 0, 2, 2, 2, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0]
            for (int i = 0; i < 42; i++) {
                // count[i] >= 2 这是说明该牌至少有两个是对子或者三个的
                if (count[i] >= 2) {
                    // 这里将该牌的数量-2
                    count[i] -= 2;
                    // 继续循环判断是否能胡牌
                    if (tryHU(count, len - 2)) {
                        return true;
                    }
                    // 如果这里不能胡牌将数量加回去
                    count[i] += 2;
                }
            }
        } else {
            // 三个一样的 刻子 这是判断30位上的count的每一位上的牌是否有3张以上的
            for (int i = 0; i < 42; i++) {
                if (count[i] >= 3) {
                    count[i] -= 3;
                    if (tryHU(count, len - 3)) {
                        return true;
                    }
                    count[i] += 3;
                }
            }

            // 是否是顺子 不能从0遍历到25而应该是0~6,9~15,18~24
            for (int i = 0; i <= 6; i++) {
                // 这里判断相邻的三位上是否有牌 如果有 则将该位的数字减1
                if (count[i] > 0 && count[i + 1] > 0 && count[i + 2] > 0) {
                    count[i] -= 1;
                    count[i + 1] -= 1;
                    count[i + 2] -= 1;
                    if (tryHU(count, len - 3)) {
                        return true;
                    }
                    count[i] += 1;
                    count[i + 1] += 1;
                    count[i + 2] += 1;
                }
            }
            // 是否是顺子 不能从0遍历到25而应该是0~6,9~15,18~24
            for (int i = 9; i <= 15; i++) {
                if (count[i] > 0 && count[i + 1] > 0 && count[i + 2] > 0) {
                    count[i] -= 1;
                    count[i + 1] -= 1;
                    count[i + 2] -= 1;
                    if (tryHU(count, len - 3)) {
                        return true;
                    }
                    count[i] += 1;
                    count[i + 1] += 1;
                    count[i + 2] += 1;
                }
            }
            // 是否是顺子 不能从0遍历到25而应该是0~6,9~15,18~24
            for (int i = 18; i <= 24; i++) {
                if (count[i] > 0 && count[i + 1] > 0 && count[i + 2] > 0) {
                    count[i] -= 1;
                    count[i + 1] -= 1;
                    count[i + 2] -= 1;
                    if (tryHU(count, len - 3)) {
                        return true;
                    }
                    count[i] += 1;
                    count[i + 1] += 1;
                    count[i + 2] += 1;
                }
            }
        }
        return false;
    }


}
