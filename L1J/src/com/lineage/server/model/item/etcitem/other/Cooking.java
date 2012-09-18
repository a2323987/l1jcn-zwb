package com.lineage.server.model.item.etcitem.other;

import com.lineage.server.model.L1Cooking;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;

/**
 * 料理、象牙塔妙药<br>
 * 41277 - 漂浮之眼肉排 <br>
 * 41278 - 烤熊肉 <br>
 * 41279 - 煎饼 <br>
 * 41280 - 烤蚂蚁腿起司 <br>
 * 41281 - 水果沙拉 <br>
 * 41282 - 水果糖醋肉 <br>
 * 41283 - 烤山猪肉串 <br>
 * 41284 - 蘑菇汤 <br>
 * 41285 - 特别的漂浮之眼肉排 <br>
 * 41286 - 特别的烤熊肉 <br>
 * 41287 - 特别的煎饼 <br>
 * 41288 - 特别的烤蚂蚁腿起司 <br>
 * 41289 - 特别的水果沙拉 <br>
 * 41290 - 特别的水果糖醋肉 <br>
 * 41291 - 特别的烤山猪肉串 <br>
 * 41292 - 特别的蘑菇汤 <br>
 * 49049 - 鱼子酱 <br>
 * 49050 - 鳄鱼肉排 <br>
 * 49051 - 龙龟蛋饼干 <br>
 * 49052 - 烤奇异鹦鹉 <br>
 * 49053 - 毒蝎串烧 <br>
 * 49054 - 炖伊莱克顿 <br>
 * 49055 - 蜘蛛腿串烧 <br>
 * 49056 - 蟹肉汤 <br>
 * 49057 - 特别的鱼子酱 <br>
 * 49058 - 特别的鳄鱼肉排 <br>
 * 49059 - 特别的龙龟蛋饼干 <br>
 * 49060 - 特别的烤奇异鹦鹉 <br>
 * 49061 - 特别的毒蝎串烧 <br>
 * 49062 - 特别的炖伊莱克顿 <br>
 * 49063 - 特别的蜘蛛腿串烧 <br>
 * 49064 - 特别的蟹肉汤 <br>
 * 49244 - 烤奎斯坦修的螯 <br>
 * 49245 - 烤格利芬肉 <br>
 * 49246 - 亚力安的尾巴肉排 <br>
 * 49247 - 烤巨王龟肉 <br>
 * 49248 - 幼龙翅膀串烧 <br>
 * 49249 - 烤飞龙肉 <br>
 * 49250 - 炖深海鱼肉 <br>
 * 49251 - 邪恶蜥蜴蛋汤 <br>
 * 49252 - 特别的烤奎斯坦修的螯 <br>
 * 49253 - 特别的烤格利芬肉 <br>
 * 49254 - 特别的亚力安的尾巴肉排 <br>
 * 49255 - 特别的烤巨王龟肉 <br>
 * 49256 - 特别的幼龙翅膀串烧 <br>
 * 49257 - 特别的烤飞龙肉 <br>
 * 49258 - 特别的炖深海鱼肉 <br>
 * 49259 - 特别的邪恶蜥蜴蛋汤 <br>
 * 49315 - 象牙塔妙药 <br>
 * 
 * @author jrwz
 */
public class Cooking implements ItemExecutor {

    public static ItemExecutor get() {
        return new Cooking();
    }

    private Cooking() {
    }

    /**
     * 道具执行
     * 
     * @param data
     *            参数
     * @param pc
     *            对象
     * @param item
     *            道具
     */
    @Override
    public void execute(final int[] data, final L1PcInstance pc,
            final L1ItemInstance item) {

        L1Cooking.useCookingItem(pc, item);
    }
}
