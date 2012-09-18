package com.lineage.server.model.item.etcitem.quest;

import com.lineage.Config;
import com.lineage.server.model.L1Quest;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_NPCTalkReturn;

/**
 * 任务对话视窗
 * 
 * @author jrwz
 */
public class HTML implements ItemExecutor {

    public static ItemExecutor get() {
        return new HTML();
    }

    private HTML() {
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

        final int itemId = item.getItemId();

        switch (itemId) {

            case 40641: // 说话卷轴
                if (Config.ALT_TALKINGSCROLLQUEST == true) {
                    switch (pc.getQuest().get_step(L1Quest.QUEST_TOSCROLL)) {
                        case 0:
                            pc.sendPackets(new S_NPCTalkReturn(pc.getId(),
                                    "tscrolla"));
                            break;

                        case 1:
                            pc.sendPackets(new S_NPCTalkReturn(pc.getId(),
                                    "tscrollb"));
                            break;

                        case 2:
                            pc.sendPackets(new S_NPCTalkReturn(pc.getId(),
                                    "tscrollc"));
                            break;

                        case 3:
                            pc.sendPackets(new S_NPCTalkReturn(pc.getId(),
                                    "tscrolld"));
                            break;

                        case 4:
                            pc.sendPackets(new S_NPCTalkReturn(pc.getId(),
                                    "tscrolle"));
                            break;

                        case 5:
                            pc.sendPackets(new S_NPCTalkReturn(pc.getId(),
                                    "tscrollf"));
                            break;

                        case 6:
                            pc.sendPackets(new S_NPCTalkReturn(pc.getId(),
                                    "tscrollg"));
                            break;

                        case 7:
                            pc.sendPackets(new S_NPCTalkReturn(pc.getId(),
                                    "tscrollh"));
                            break;

                        case 8:
                            pc.sendPackets(new S_NPCTalkReturn(pc.getId(),
                                    "tscrolli"));
                            break;

                        case 9:
                            pc.sendPackets(new S_NPCTalkReturn(pc.getId(),
                                    "tscrollj"));
                            break;

                        case 10:
                            pc.sendPackets(new S_NPCTalkReturn(pc.getId(),
                                    "tscrollk"));
                            break;

                        case 11:
                            pc.sendPackets(new S_NPCTalkReturn(pc.getId(),
                                    "tscrolll"));
                            break;

                        case 12:
                            pc.sendPackets(new S_NPCTalkReturn(pc.getId(),
                                    "tscrollm"));
                            break;

                        case 13:
                            pc.sendPackets(new S_NPCTalkReturn(pc.getId(),
                                    "tscrolln"));
                            break;

                        case 255:
                            pc.sendPackets(new S_NPCTalkReturn(pc.getId(),
                                    "tscrollo"));
                            break;
                        default:
                            pc.sendPackets(new S_NPCTalkReturn(pc.getId(),
                                    "tscrollp"));
                            break;
                    }
                }
                break;

            case 49172: // 希莲恩的第一次信件
                pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "silrein1lt"));
                break;

            case 49173: // 希莲恩的第二次信件
                pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "silrein2lt"));
                break;

            case 49174: // 希莲恩的第三次信件
                pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "silrein3lt"));
                break;

            case 49175: // 希莲恩的第四次信件
                pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "silrein4lt"));
                break;

            case 49176: // 希莲恩的第五次信件
                pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "silrein5lt"));
                break;

            case 49177: // 希莲恩的第六次信件
                pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "silrein6lt"));
                break;

            case 49206: // 塞维斯邪念碎片
                pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "bluesoul_p"));
                break;

            case 49210: // 普洛凯尔的第一次指令书
                pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "first_p"));
                break;

            case 49211: // 普洛凯尔的第二次指令书
                pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "second_p"));
                break;

            case 49212: // 普洛凯尔的第三次指令书
                pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "third_p"));
                break;

            case 49287: // 普洛凯尔的第四次指令书
                pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "fourth_p"));
                break;

            case 49288: // 普洛凯尔的第五次指令书
                pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "fifth_p"));
                break;

            case 49231: // 路西尔斯邪念碎片
                pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "redsoul_p"));
                break;

            case 41060: // 诺曼阿吐巴的信
                pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "nonames"));
                break;

            case 41061: // 妖精调查书：卡麦都达玛拉
                pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "kames"));
                break;

            case 41062: // 人类调查书：巴库摩那鲁加
                pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "bakumos"));
                break;

            case 41063: // 精灵调查书：可普都达玛拉
                pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "bukas"));
                break;

            case 41064: // 妖魔调查书：弧邬牟那鲁加
                pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "huwoomos"));
                break;

            case 41065: // 死亡之树调查书：诺亚阿吐巴
                pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "noas"));
                break;

            case 41356: // 波伦的资源清单
                pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "rparum3"));
                break;

            case 40701: // 小藏宝图
                switch (pc.getQuest().get_step(L1Quest.QUEST_LUKEIN1)) {

                    case 1:
                        pc.sendPackets(new S_NPCTalkReturn(pc.getId(),
                                "firsttmap"));
                        break;

                    case 2:
                        pc.sendPackets(new S_NPCTalkReturn(pc.getId(),
                                "secondtmapa"));
                        break;
                    case 3:
                        pc.sendPackets(new S_NPCTalkReturn(pc.getId(),
                                "secondtmapb"));
                        break;

                    case 4:
                        pc.sendPackets(new S_NPCTalkReturn(pc.getId(),
                                "secondtmapc"));
                        break;

                    case 5:
                        pc.sendPackets(new S_NPCTalkReturn(pc.getId(),
                                "thirdtmapd"));
                        break;

                    case 6:
                        pc.sendPackets(new S_NPCTalkReturn(pc.getId(),
                                "thirdtmape"));
                        break;
                    case 7:
                        pc.sendPackets(new S_NPCTalkReturn(pc.getId(),
                                "thirdtmapf"));
                        break;
                    case 8:
                        pc.sendPackets(new S_NPCTalkReturn(pc.getId(),
                                "thirdtmapg"));
                        break;
                    case 9:
                        pc.sendPackets(new S_NPCTalkReturn(pc.getId(),
                                "thirdtmaph"));
                        break;

                    case 10:
                        pc.sendPackets(new S_NPCTalkReturn(pc.getId(),
                                "thirdtmapi"));
                        break;
                }
                break;

            case 40663: // 儿子的信
                pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "sonsletter"));
                break;

            case 40630: // 迪哥的旧日记
                pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "diegodiary"));
                break;

            case 41340: // 佣兵团长多文的推荐书
                pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "tion"));
                break;

            case 41317: // 拉罗森的推荐书
                pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "rarson"));
                break;

            case 41318: // 可恩的便条纸
                pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "kuen"));
                break;

            case 41329: // 标本制作委托书
                pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "anirequest"));
                break;

            case 41346: // 罗宾孙的便条纸
                pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "robinscroll"));
                break;

            case 41347: // 罗宾孙的便条纸
                pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "robinscroll2"));
                break;

            case 41348: // 罗宾孙的推荐书
                pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "robinhood"));
                break;

            case 41007: // 伊莉丝的命令书：灵魂之安息
                pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "erisscroll"));
                break;

            case 41009: // 伊莉丝的命令书：同盟之意志
                pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "erisscroll2"));
                break;

            case 41019: // 拉斯塔巴德历史书第1页
                pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "lashistory1"));
                break;

            case 41020: // 拉斯塔巴德历史书第2页
                pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "lashistory2"));
                break;

            case 41021: // 拉斯塔巴德历史书第3页
                pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "lashistory3"));
                break;

            case 41022: // 拉斯塔巴德历史书第4页
                pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "lashistory4"));
                break;

            case 41023: // 拉斯塔巴德历史书第5页
                pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "lashistory5"));
                break;

            case 41024: // 拉斯塔巴德历史书第6页
                pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "lashistory6"));
                break;

            case 41025: // 拉斯塔巴德历史书第7页
                pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "lashistory7"));
                break;

            case 41026: // 拉斯塔巴德历史书第8页
                pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "lashistory8"));
                break;
        }
    }
}
