/**
 *                            License
 * THE WORK (AS DEFINED BELOW) IS PROVIDED UNDER THE TERMS OF THIS  
 * CREATIVE COMMONS PUBLIC LICENSE ("CCPL" OR "LICENSE"). 
 * THE WORK IS PROTECTED BY COPYRIGHT AND/OR OTHER APPLICABLE LAW.  
 * ANY USE OF THE WORK OTHER THAN AS AUTHORIZED UNDER THIS LICENSE OR  
 * COPYRIGHT LAW IS PROHIBITED.
 * 
 * BY EXERCISING ANY RIGHTS TO THE WORK PROVIDED HERE, YOU ACCEPT AND  
 * AGREE TO BE BOUND BY THE TERMS OF THIS LICENSE. TO THE EXTENT THIS LICENSE  
 * MAY BE CONSIDERED TO BE A CONTRACT, THE LICENSOR GRANTS YOU THE RIGHTS CONTAINED 
 * HERE IN CONSIDERATION OF YOUR ACCEPTANCE OF SUCH TERMS AND CONDITIONS.
 * 
 */
package com.lineage.server;

import java.util.logging.Logger;

import com.lineage.server.datatables.UBTable;
import com.lineage.server.model.L1UltimateBattle;

/**
 * 无限大战时间控制器
 */
public class UbTimeController implements Runnable {

    private static Logger _log = Logger.getLogger(UbTimeController.class
            .getName());

    private static UbTimeController _instance;

    public static UbTimeController getInstance() {
        if (_instance == null) {
            _instance = new UbTimeController();
        }
        return _instance;
    }

    /** 检查无限大战时间 */
    private void checkUbTime() {
        for (final L1UltimateBattle ub : UBTable.getInstance().getAllUb()) {
            if (ub.checkUbTime() && !ub.isActive()) {
                ub.start(); // 无限大战开始
            }
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                this.checkUbTime(); // 开始检查无限大战的时间
                Thread.sleep(15000);
            }
        } catch (final Exception e1) {
            _log.warning(e1.getMessage());
        }
    }

}
