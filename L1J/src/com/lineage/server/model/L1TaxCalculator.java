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
package com.lineage.server.model;

/**
 * 税率计算
 */
public class L1TaxCalculator {
    /**
     * 战争税15%固定
     */
    private static final int WAR_TAX_RATES = 15;

    /**
     * 国税は10%固定（地域税に対する割合）
     */
    private static final int NATIONAL_TAX_RATES = 10;

    /**
     * ディアド税は10%固定（戦争税に対する割合）
     */
    private static final int DIAD_TAX_RATES = 10;

    private final int _taxRatesCastle;
    private final int _taxRatesTown;
    private final int _taxRatesWar = WAR_TAX_RATES;

    /**
     * @param merchantNpcId
     *            計算対象商店のNPCID
     */
    public L1TaxCalculator(final int merchantNpcId) {
        this._taxRatesCastle = L1CastleLocation
                .getCastleTaxRateByNpcId(merchantNpcId);
        this._taxRatesTown = L1TownLocation
                .getTownTaxRateByNpcid(merchantNpcId);
    }

    // XXX 個別に計算する為、丸め誤差が出る。
    public int calcCastleTaxPrice(final int price) {
        return (price * this._taxRatesCastle) / 100
                - this.calcNationalTaxPrice(price);
    }

    public int calcDiadTaxPrice(final int price) {
        return (price * this._taxRatesWar) / 100 / (100 / DIAD_TAX_RATES);
    }

    public int calcNationalTaxPrice(final int price) {
        return (price * this._taxRatesCastle) / 100
                / (100 / NATIONAL_TAX_RATES);
    }

    public int calcTotalTaxPrice(final int price) {
        final int taxCastle = price * this._taxRatesCastle;
        final int taxTown = price * this._taxRatesTown;
        final int taxWar = price * WAR_TAX_RATES;
        return (taxCastle + taxTown + taxWar) / 100;
    }

    public int calcTownTaxPrice(final int price) {
        return (price * this._taxRatesTown) / 100;
    }

    public int calcWarTaxPrice(final int price) {
        return (price * this._taxRatesWar) / 100;
    }

    /**
     * 确定计税价格。
     * 
     * @param price
     *            税前价格
     * @return 税后价格
     */
    public int layTax(final int price) {
        return price + this.calcTotalTaxPrice(price);
    }
}
