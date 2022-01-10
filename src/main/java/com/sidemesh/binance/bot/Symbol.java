package com.sidemesh.binance.bot;

import com.sidemesh.binance.bot.util.StringEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.math.BigDecimal;

public enum Symbol implements StringEnum<Symbol> {

    // generate code from binance api
    // generate code from binance api
    // generate code from binance api
    
    ETH_BTC("ETH","BTC", "0.000001", "0.0001"),
    
    LTC_BTC("LTC","BTC", "0.000001", "0.001"),
    
    BNB_BTC("BNB","BTC", "0.000001", "0.001"),
    
    NEO_BTC("NEO","BTC", "0.000001", "0.01"),
    
    QTUM_ETH("QTUM","ETH", "0.000001", "0.1"),
    
    EOS_ETH("EOS","ETH", "0.000001", "0.1"),
    
    SNT_ETH("SNT","ETH", "0.00000001", "1"),
    
    BNT_ETH("BNT","ETH", "0.000001", "0.1"),
    
    GAS_BTC("GAS","BTC", "0.0000001", "0.1"),
    
    BNB_ETH("BNB","ETH", "0.0001", "0.001"),
    
    BTC_USDT("BTC","USDT", "0.01", "0.00001"),
    
    ETH_USDT("ETH","USDT", "0.01", "0.0001"),
    
    WTC_BTC("WTC","BTC", "0.00000001", "1"),
    
    LRC_BTC("LRC","BTC", "0.00000001", "1"),
    
    LRC_ETH("LRC","ETH", "0.00000001", "1"),
    
    QTUM_BTC("QTUM","BTC", "0.0000001", "0.1"),
    
    YOYO_BTC("YOYO","BTC", "0.00000001", "1"),
    
    OMG_BTC("OMG","BTC", "0.0000001", "0.1"),
    
    OMG_ETH("OMG","ETH", "0.000001", "0.1"),
    
    ZRX_BTC("ZRX","BTC", "0.00000001", "1"),
    
    ZRX_ETH("ZRX","ETH", "0.0000001", "1"),
    
    KNC_BTC("KNC","BTC", "0.00000001", "0.1"),
    
    KNC_ETH("KNC","ETH", "0.0000001", "0.1"),
    
    FUN_ETH("FUN","ETH", "0.00000001", "1"),
    
    SNM_BTC("SNM","BTC", "0.00000001", "1"),
    
    NEO_ETH("NEO","ETH", "0.00001", "0.01"),
    
    IOTA_BTC("IOTA","BTC", "0.00000001", "1"),
    
    IOTA_ETH("IOTA","ETH", "0.0000001", "1"),
    
    LINK_BTC("LINK","BTC", "0.0000001", "0.01"),
    
    LINK_ETH("LINK","ETH", "0.000001", "0.01"),
    
    XVG_BTC("XVG","BTC", "0.00000001", "1"),
    
    XVG_ETH("XVG","ETH", "0.00000001", "1"),
    
    MDA_BTC("MDA","BTC", "0.00000001", "1"),
    
    MTL_BTC("MTL","BTC", "0.00000001", "0.1"),
    
    MTL_ETH("MTL","ETH", "0.0000001", "0.1"),
    
    EOS_BTC("EOS","BTC", "0.0000001", "0.1"),
    
    SNT_BTC("SNT","BTC", "0.00000001", "1"),
    
    ETC_ETH("ETC","ETH", "0.00001", "0.01"),
    
    ETC_BTC("ETC","BTC", "0.000001", "0.01"),
    
    MTH_BTC("MTH","BTC", "0.00000001", "1"),
    
    DNT_BTC("DNT","BTC", "0.00000001", "1"),
    
    ZEC_BTC("ZEC","BTC", "0.000001", "0.001"),
    
    ZEC_ETH("ZEC","ETH", "0.00001", "0.001"),
    
    BNT_BTC("BNT","BTC", "0.00000001", "0.1"),
    
    AST_BTC("AST","BTC", "0.00000001", "1"),
    
    DASH_BTC("DASH","BTC", "0.000001", "0.001"),
    
    DASH_ETH("DASH","ETH", "0.00001", "0.001"),
    
    OAX_BTC("OAX","BTC", "0.00000001", "1"),
    
    BTG_BTC("BTG","BTC", "0.000001", "0.01"),
    
    REQ_BTC("REQ","BTC", "0.00000001", "1"),
    
    VIB_BTC("VIB","BTC", "0.00000001", "1"),
    
    VIB_ETH("VIB","ETH", "0.00000001", "1"),
    
    TRX_BTC("TRX","BTC", "0.00000001", "1"),
    
    TRX_ETH("TRX","ETH", "0.00000001", "1"),
    
    POWR_BTC("POWR","BTC", "0.00000001", "1"),
    
    POWR_ETH("POWR","ETH", "0.0000001", "1"),
    
    ARK_BTC("ARK","BTC", "0.00000001", "0.1"),
    
    XRP_BTC("XRP","BTC", "0.00000001", "1"),
    
    XRP_ETH("XRP","ETH", "0.0000001", "1"),
    
    ENJ_BTC("ENJ","BTC", "0.00000001", "0.1"),
    
    ENJ_ETH("ENJ","ETH", "0.0000001", "0.1"),
    
    STORJ_BTC("STORJ","BTC", "0.00000001", "1"),
    
    BNB_USDT("BNB","USDT", "0.1", "0.001"),
    
    KMD_BTC("KMD","BTC", "0.00000001", "1"),
    
    KMD_ETH("KMD","ETH", "0.0000001", "1"),
    
    NULS_BTC("NULS","BTC", "0.00000001", "1"),
    
    XMR_BTC("XMR","BTC", "0.000001", "0.001"),
    
    XMR_ETH("XMR","ETH", "0.00001", "0.001"),
    
    AMB_BTC("AMB","BTC", "0.00000001", "1"),
    
    BAT_BTC("BAT","BTC", "0.00000001", "1"),
    
    BAT_ETH("BAT","ETH", "0.0000001", "1"),
    
    GXS_BTC("GXS","BTC", "0.00000001", "1"),
    
    GXS_ETH("GXS","ETH", "0.0000001", "1"),
    
    NEO_USDT("NEO","USDT", "0.01", "0.01"),
    
    NEO_BNB("NEO","BNB", "0.0001", "0.01"),
    
    QSP_BTC("QSP","BTC", "0.00000001", "1"),
    
    QSP_ETH("QSP","ETH", "0.00000001", "1"),
    
    BTS_BTC("BTS","BTC", "0.00000001", "1"),
    
    LSK_BTC("LSK","BTC", "0.00000001", "0.1"),
    
    LSK_ETH("LSK","ETH", "0.000001", "0.1"),
    
    MANA_BTC("MANA","BTC", "0.00000001", "1"),
    
    MANA_ETH("MANA","ETH", "0.0000001", "1"),
    
    BCD_BTC("BCD","BTC", "0.00000001", "0.1"),
    
    IOTA_BNB("IOTA","BNB", "0.000001", "1"),
    
    ADX_BTC("ADX","BTC", "0.00000001", "1"),
    
    ADX_ETH("ADX","ETH", "0.0000001", "1"),
    
    ADA_BTC("ADA","BTC", "0.00000001", "0.1"),
    
    ADA_ETH("ADA","ETH", "0.0000001", "0.1"),
    
    XLM_BTC("XLM","BTC", "0.00000001", "1"),
    
    XLM_ETH("XLM","ETH", "0.0000001", "1"),
    
    XLM_BNB("XLM","BNB", "0.0000001", "1"),
    
    CND_BTC("CND","BTC", "0.00000001", "1"),
    
    WABI_BTC("WABI","BTC", "0.00000001", "1"),
    
    WABI_BNB("WABI","BNB", "0.0000001", "1"),
    
    LTC_ETH("LTC","ETH", "0.00001", "0.001"),
    
    LTC_USDT("LTC","USDT", "0.1", "0.001"),
    
    LTC_BNB("LTC","BNB", "0.0001", "0.001"),
    
    WAVES_BTC("WAVES","BTC", "0.0000001", "0.01"),
    
    WAVES_ETH("WAVES","ETH", "0.000001", "0.01"),
    
    WAVES_BNB("WAVES","BNB", "0.00001", "0.01"),
    
    GTO_BTC("GTO","BTC", "0.00000001", "1"),
    
    ICX_BTC("ICX","BTC", "0.00000001", "1"),
    
    ICX_ETH("ICX","ETH", "0.0000001", "1"),
    
    ELF_BTC("ELF","BTC", "0.00000001", "1"),
    
    ELF_ETH("ELF","ETH", "0.0000001", "1"),
    
    AION_BTC("AION","BTC", "0.00000001", "1"),
    
    AION_ETH("AION","ETH", "0.00000001", "1"),
    
    NEBL_BTC("NEBL","BTC", "0.00000001", "0.1"),
    
    NEBL_ETH("NEBL","ETH", "0.0000001", "1"),
    
    BRD_BTC("BRD","BTC", "0.00000001", "1"),
    
    BRD_ETH("BRD","ETH", "0.00000001", "1"),
    
    NAV_BTC("NAV","BTC", "0.00000001", "1"),
    
    RLC_BTC("RLC","BTC", "0.00000001", "0.1"),
    
    RLC_ETH("RLC","ETH", "0.000001", "0.1"),
    
    PIVX_BTC("PIVX","BTC", "0.00000001", "1"),
    
    PIVX_ETH("PIVX","ETH", "0.0000001", "1"),
    
    IOST_BTC("IOST","BTC", "0.00000001", "1"),
    
    IOST_ETH("IOST","ETH", "0.00000001", "1"),
    
    STEEM_BTC("STEEM","BTC", "0.00000001", "1"),
    
    STEEM_ETH("STEEM","ETH", "0.0000001", "1"),
    
    NANO_BTC("NANO","BTC", "0.0000001", "0.1"),
    
    NANO_ETH("NANO","ETH", "0.000001", "0.1"),
    
    BLZ_BTC("BLZ","BTC", "0.00000001", "1"),
    
    BLZ_ETH("BLZ","ETH", "0.00000001", "1"),
    
    NCASH_ETH("NCASH","ETH", "0.00000001", "1"),
    
    ZIL_BTC("ZIL","BTC", "0.00000001", "1"),
    
    ZIL_ETH("ZIL","ETH", "0.00000001", "1"),
    
    ZIL_BNB("ZIL","BNB", "0.0000001", "1"),
    
    ONT_BTC("ONT","BTC", "0.00000001", "1"),
    
    ONT_ETH("ONT","ETH", "0.0000001", "1"),
    
    QTUM_USDT("QTUM","USDT", "0.001", "0.1"),
    
    XEM_BTC("XEM","BTC", "0.00000001", "1"),
    
    XEM_ETH("XEM","ETH", "0.00000001", "1"),
    
    WAN_BTC("WAN","BTC", "0.00000001", "1"),
    
    WAN_ETH("WAN","ETH", "0.0000001", "1"),
    
    QLC_BTC("QLC","BTC", "0.00000001", "1"),
    
    QLC_ETH("QLC","ETH", "0.00000001", "1"),
    
    SYS_BTC("SYS","BTC", "0.00000001", "1"),
    
    GRS_BTC("GRS","BTC", "0.00000001", "1"),
    
    ADA_USDT("ADA","USDT", "0.001", "0.1"),
    
    ADA_BNB("ADA","BNB", "0.000001", "0.1"),
    
    LOOM_BTC("LOOM","BTC", "0.00000001", "1"),
    
    LOOM_ETH("LOOM","ETH", "0.00000001", "1"),
    
    XRP_USDT("XRP","USDT", "0.0001", "1"),
    
    REP_BTC("REP","BTC", "0.0000001", "0.01"),
    
    REP_ETH("REP","ETH", "0.000001", "0.01"),
    
    BTC_TUSD("BTC","TUSD", "0.01", "0.00001"),
    
    ETH_TUSD("ETH","TUSD", "0.01", "0.0001"),
    
    ZEN_BTC("ZEN","BTC", "0.000001", "0.01"),
    
    ZEN_ETH("ZEN","ETH", "0.00001", "0.01"),
    
    ZEN_BNB("ZEN","BNB", "0.0001", "0.01"),
    
    EOS_USDT("EOS","USDT", "0.001", "0.1"),
    
    EOS_BNB("EOS","BNB", "0.00001", "0.1"),
    
    CVC_BTC("CVC","BTC", "0.00000001", "1"),
    
    CVC_ETH("CVC","ETH", "0.0000001", "1"),
    
    THETA_BTC("THETA","BTC", "0.0000001", "0.1"),
    
    THETA_ETH("THETA","ETH", "0.000001", "0.1"),
    
    THETA_BNB("THETA","BNB", "0.00001", "0.1"),
    
    XRP_BNB("XRP","BNB", "0.000001", "1"),
    
    TUSD_USDT("TUSD","USDT", "0.0001", "1"),
    
    IOTA_USDT("IOTA","USDT", "0.0001", "1"),
    
    XLM_USDT("XLM","USDT", "0.0001", "1"),
    
    IOTX_BTC("IOTX","BTC", "0.00000001", "1"),
    
    IOTX_ETH("IOTX","ETH", "0.00000001", "1"),
    
    QKC_BTC("QKC","BTC", "0.00000001", "1"),
    
    QKC_ETH("QKC","ETH", "0.00000001", "1"),
    
    NXS_BTC("NXS","BTC", "0.00000001", "1"),
    
    ENJ_BNB("ENJ","BNB", "0.000001", "0.1"),
    
    DATA_BTC("DATA","BTC", "0.00000001", "1"),
    
    DATA_ETH("DATA","ETH", "0.00000001", "1"),
    
    ONT_USDT("ONT","USDT", "0.0001", "1"),
    
    TRX_BNB("TRX","BNB", "0.0000001", "1"),
    
    TRX_USDT("TRX","USDT", "0.00001", "0.1"),
    
    ETC_USDT("ETC","USDT", "0.01", "0.01"),
    
    ETC_BNB("ETC","BNB", "0.0001", "0.01"),
    
    ICX_USDT("ICX","USDT", "0.001", "0.1"),
    
    SC_BTC("SC","BTC", "0.00000001", "1"),
    
    SC_ETH("SC","ETH", "0.00000001", "1"),
    
    SC_BNB("SC","BNB", "0.00000001", "1"),
    
    KEY_ETH("KEY","ETH", "0.00000001", "1"),
    
    NAS_BTC("NAS","BTC", "0.0000001", "1"),
    
    NAS_ETH("NAS","ETH", "0.0000001", "1"),
    
    MFT_ETH("MFT","ETH", "0.00000001", "1"),
    
    MFT_BNB("MFT","BNB", "0.00000001", "1"),
    
    DENT_ETH("DENT","ETH", "0.00000001", "1"),
    
    ARDR_BTC("ARDR","BTC", "0.00000001", "1"),
    
    NULS_USDT("NULS","USDT", "0.0001", "1"),
    
    HOT_ETH("HOT","ETH", "0.00000001", "1"),
    
    VET_BTC("VET","BTC", "0.00000001", "1"),
    
    VET_ETH("VET","ETH", "0.00000001", "1"),
    
    VET_USDT("VET","USDT", "0.00001", "0.1"),
    
    VET_BNB("VET","BNB", "0.0000001", "1"),
    
    DOCK_BTC("DOCK","BTC", "0.00000001", "1"),
    
    POLY_BTC("POLY","BTC", "0.00000001", "1"),
    
    GO_BTC("GO","BTC", "0.00000001", "1"),
    
    RVN_BTC("RVN","BTC", "0.00000001", "1"),
    
    RVN_BNB("RVN","BNB", "0.0000001", "1"),
    
    DCR_BTC("DCR","BTC", "0.000001", "0.001"),
    
    MITH_BTC("MITH","BTC", "0.00000001", "1"),
    
    MITH_BNB("MITH","BNB", "0.0000001", "1"),
    
    REN_BTC("REN","BTC", "0.00000001", "1"),
    
    BNB_TUSD("BNB","TUSD", "0.1", "0.001"),
    
    XRP_TUSD("XRP","TUSD", "0.0001", "1"),
    
    BNB_USDC("BNB","USDC", "0.1", "0.001"),
    
    BTC_USDC("BTC","USDC", "0.01", "0.00001"),
    
    ETH_USDC("ETH","USDC", "0.01", "0.0001"),
    
    XRP_USDC("XRP","USDC", "0.0001", "1"),
    
    EOS_USDC("EOS","USDC", "0.001", "0.1"),
    
    USDC_USDT("USDC","USDT", "0.0001", "1"),
    
    ADA_TUSD("ADA","TUSD", "0.001", "0.1"),
    
    TRX_TUSD("TRX","TUSD", "0.00001", "0.1"),
    
    TRX_XRP("TRX","XRP", "0.00001", "0.1"),
    
    LINK_USDT("LINK","USDT", "0.01", "0.01"),
    
    LINK_USDC("LINK","USDC", "0.01", "0.01"),
    
    WAVES_USDT("WAVES","USDT", "0.01", "0.01"),
    
    LTC_USDC("LTC","USDC", "0.1", "0.001"),
    
    TRX_USDC("TRX","USDC", "0.00001", "0.1"),
    
    BTT_BNB("BTT","BNB", "0.00000001", "1"),
    
    BTT_USDT("BTT","USDT", "0.000001", "1"),
    
    BTT_TUSD("BTT","TUSD", "0.000001", "1"),
    
    BTT_USDC("BTT","USDC", "0.000001", "1"),
    
    ONG_BTC("ONG","BTC", "0.00000001", "1"),
    
    ONG_USDT("ONG","USDT", "0.0001", "1"),
    
    HOT_BNB("HOT","BNB", "0.00000001", "1"),
    
    HOT_USDT("HOT","USDT", "0.000001", "1"),
    
    ZIL_USDT("ZIL","USDT", "0.00001", "0.1"),
    
    ZRX_USDT("ZRX","USDT", "0.0001", "1"),
    
    FET_BNB("FET","BNB", "0.000001", "1"),
    
    FET_BTC("FET","BTC", "0.00000001", "1"),
    
    FET_USDT("FET","USDT", "0.0001", "1"),
    
    BAT_USDT("BAT","USDT", "0.0001", "1"),
    
    XMR_BNB("XMR","BNB", "0.0001", "0.001"),
    
    XMR_USDT("XMR","USDT", "0.1", "0.001"),
    
    ZEC_BNB("ZEC","BNB", "0.0001", "0.001"),
    
    ZEC_USDT("ZEC","USDT", "0.1", "0.001"),
    
    ZEC_USDC("ZEC","USDC", "0.1", "0.001"),
    
    IOST_BNB("IOST","BNB", "0.00000001", "1"),
    
    IOST_USDT("IOST","USDT", "0.00001", "1"),
    
    CELR_BNB("CELR","BNB", "0.00000001", "1"),
    
    CELR_BTC("CELR","BTC", "0.00000001", "1"),
    
    CELR_USDT("CELR","USDT", "0.00001", "0.1"),
    
    ADA_USDC("ADA","USDC", "0.001", "0.1"),
    
    DASH_BNB("DASH","BNB", "0.0001", "0.001"),
    
    DASH_USDT("DASH","USDT", "0.1", "0.001"),
    
    NANO_USDT("NANO","USDT", "0.001", "0.1"),
    
    OMG_USDT("OMG","USDT", "0.001", "0.1"),
    
    THETA_USDT("THETA","USDT", "0.001", "0.1"),
    
    ENJ_USDT("ENJ","USDT", "0.001", "0.1"),
    
    MITH_USDT("MITH","USDT", "0.00001", "0.1"),
    
    MATIC_BNB("MATIC","BNB", "0.000001", "0.1"),
    
    MATIC_BTC("MATIC","BTC", "0.00000001", "0.1"),
    
    MATIC_USDT("MATIC","USDT", "0.001", "0.1"),
    
    ATOM_BNB("ATOM","BNB", "0.00001", "0.01"),
    
    ATOM_BTC("ATOM","BTC", "0.0000001", "0.01"),
    
    ATOM_USDT("ATOM","USDT", "0.01", "0.01"),
    
    ATOM_USDC("ATOM","USDC", "0.01", "0.01"),
    
    PHB_BTC("PHB","BTC", "0.00000001", "0.1"),
    
    PHB_TUSD("PHB","TUSD", "0.001", "0.1"),
    
    TFUEL_BTC("TFUEL","BTC", "0.00000001", "1"),
    
    TFUEL_USDT("TFUEL","USDT", "0.0001", "1"),
    
    ONE_BNB("ONE","BNB", "0.0000001", "1"),
    
    ONE_BTC("ONE","BTC", "0.00000001", "1"),
    
    ONE_USDT("ONE","USDT", "0.00001", "0.1"),
    
    FTM_BNB("FTM","BNB", "0.0000001", "1"),
    
    FTM_BTC("FTM","BTC", "0.00000001", "1"),
    
    FTM_USDT("FTM","USDT", "0.0001", "1"),
    
    ALGO_BNB("ALGO","BNB", "0.000001", "1"),
    
    ALGO_BTC("ALGO","BTC", "0.00000001", "1"),
    
    ALGO_USDT("ALGO","USDT", "0.0001", "1"),
    
    GTO_USDT("GTO","USDT", "0.00001", "0.1"),
    
    DOGE_BTC("DOGE","BTC", "0.00000001", "1"),
    
    DOGE_USDT("DOGE","USDT", "0.0001", "1"),
    
    DUSK_BTC("DUSK","BTC", "0.00000001", "1"),
    
    DUSK_USDT("DUSK","USDT", "0.0001", "1"),
    
    ANKR_BNB("ANKR","BNB", "0.0000001", "1"),
    
    ANKR_BTC("ANKR","BTC", "0.00000001", "1"),
    
    ANKR_USDT("ANKR","USDT", "0.00001", "0.1"),
    
    WIN_BNB("WIN","BNB", "0.00000001", "1"),
    
    WIN_USDT("WIN","USDT", "0.0000001", "1"),
    
    WIN_USDC("WIN","USDC", "0.0000001", "1"),
    
    COS_BNB("COS","BNB", "0.00000001", "1"),
    
    COS_BTC("COS","BTC", "0.00000001", "1"),
    
    COS_USDT("COS","USDT", "0.00001", "0.1"),
    
    COCOS_BNB("COCOS","BNB", "0.000001", "1"),
    
    COCOS_USDT("COCOS","USDT", "0.0001", "1"),
    
    MTL_USDT("MTL","USDT", "0.001", "0.1"),
    
    TOMO_BTC("TOMO","BTC", "0.00000001", "0.1"),
    
    TOMO_USDT("TOMO","USDT", "0.001", "0.1"),
    
    PERL_BNB("PERL","BNB", "0.0000001", "1"),
    
    PERL_BTC("PERL","BTC", "0.00000001", "1"),
    
    PERL_USDT("PERL","USDT", "0.00001", "0.1"),
    
    DENT_USDT("DENT","USDT", "0.000001", "1"),
    
    MFT_USDT("MFT","USDT", "0.000001", "1"),
    
    KEY_USDT("KEY","USDT", "0.000001", "1"),
    
    DOCK_USDT("DOCK","USDT", "0.00001", "1"),
    
    WAN_USDT("WAN","USDT", "0.0001", "1"),
    
    FUN_USDT("FUN","USDT", "0.00001", "1"),
    
    CVC_USDT("CVC","USDT", "0.0001", "1"),
    
    BTT_TRX("BTT","TRX", "0.00001", "0.1"),
    
    WIN_TRX("WIN","TRX", "0.000001", "0.1"),
    
    CHZ_BNB("CHZ","BNB", "0.0000001", "1"),
    
    CHZ_BTC("CHZ","BTC", "0.00000001", "1"),
    
    CHZ_USDT("CHZ","USDT", "0.0001", "1"),
    
    BAND_BTC("BAND","BTC", "0.0000001", "0.1"),
    
    BAND_USDT("BAND","USDT", "0.001", "0.1"),
    
    BNB_BUSD("BNB","BUSD", "0.1", "0.001"),
    
    BTC_BUSD("BTC","BUSD", "0.01", "0.00001"),
    
    BUSD_USDT("BUSD","USDT", "0.0001", "1"),
    
    BEAM_BTC("BEAM","BTC", "0.00000001", "1"),
    
    BEAM_USDT("BEAM","USDT", "0.0001", "1"),
    
    XTZ_BNB("XTZ","BNB", "0.000001", "0.1"),
    
    XTZ_BTC("XTZ","BTC", "0.00000001", "0.1"),
    
    XTZ_USDT("XTZ","USDT", "0.001", "0.1"),
    
    REN_USDT("REN","USDT", "0.0001", "1"),
    
    RVN_USDT("RVN","USDT", "0.00001", "0.1"),
    
    HBAR_BNB("HBAR","BNB", "0.0000001", "1"),
    
    HBAR_BTC("HBAR","BTC", "0.00000001", "1"),
    
    HBAR_USDT("HBAR","USDT", "0.0001", "1"),
    
    NKN_BTC("NKN","BTC", "0.00000001", "1"),
    
    NKN_USDT("NKN","USDT", "0.0001", "1"),
    
    XRP_BUSD("XRP","BUSD", "0.0001", "1"),
    
    ETH_BUSD("ETH","BUSD", "0.01", "0.0001"),
    
    LTC_BUSD("LTC","BUSD", "0.1", "0.001"),
    
    LINK_BUSD("LINK","BUSD", "0.01", "0.01"),
    
    ETC_BUSD("ETC","BUSD", "0.01", "0.01"),
    
    STX_BNB("STX","BNB", "0.000001", "0.1"),
    
    STX_BTC("STX","BTC", "0.00000001", "0.1"),
    
    STX_USDT("STX","USDT", "0.001", "0.1"),
    
    KAVA_BNB("KAVA","BNB", "0.00001", "0.1"),
    
    KAVA_BTC("KAVA","BTC", "0.0000001", "0.1"),
    
    KAVA_USDT("KAVA","USDT", "0.001", "0.1"),
    
    BTC_NGN("BTC","NGN", "1", "0.00001"),
    
    ARPA_BNB("ARPA","BNB", "0.0000001", "1"),
    
    ARPA_BTC("ARPA","BTC", "0.00000001", "1"),
    
    ARPA_USDT("ARPA","USDT", "0.00001", "0.1"),
    
    TRX_BUSD("TRX","BUSD", "0.00001", "0.1"),
    
    EOS_BUSD("EOS","BUSD", "0.001", "0.1"),
    
    IOTX_USDT("IOTX","USDT", "0.00001", "1"),
    
    RLC_USDT("RLC","USDT", "0.001", "0.1"),
    
    XLM_BUSD("XLM","BUSD", "0.0001", "1"),
    
    ADA_BUSD("ADA","BUSD", "0.001", "0.1"),
    
    CTXC_BTC("CTXC","BTC", "0.00000001", "1"),
    
    CTXC_USDT("CTXC","USDT", "0.0001", "1"),
    
    BCH_BNB("BCH","BNB", "0.001", "0.001"),
    
    BCH_BTC("BCH","BTC", "0.00001", "0.001"),
    
    BCH_USDT("BCH","USDT", "0.1", "0.001"),
    
    BCH_USDC("BCH","USDC", "0.1", "0.001"),
    
    BCH_BUSD("BCH","BUSD", "0.1", "0.001"),
    
    BTC_RUB("BTC","RUB", "1", "0.00001"),
    
    ETH_RUB("ETH","RUB", "0.1", "0.0001"),
    
    XRP_RUB("XRP","RUB", "0.01", "1"),
    
    BNB_RUB("BNB","RUB", "0.01", "0.001"),
    
    TROY_BNB("TROY","BNB", "0.00000001", "1"),
    
    TROY_USDT("TROY","USDT", "0.000001", "1"),
    
    BUSD_RUB("BUSD","RUB", "0.01", "1"),
    
    QTUM_BUSD("QTUM","BUSD", "0.001", "0.1"),
    
    VET_BUSD("VET","BUSD", "0.00001", "0.1"),
    
    VITE_BTC("VITE","BTC", "0.00000001", "1"),
    
    VITE_USDT("VITE","USDT", "0.00001", "0.1"),
    
    FTT_BNB("FTT","BNB", "0.0001", "0.01"),
    
    FTT_BTC("FTT","BTC", "0.0000001", "0.01"),
    
    FTT_USDT("FTT","USDT", "0.01", "0.01"),
    
    BTC_TRY("BTC","TRY", "1", "0.00001"),
    
    BNB_TRY("BNB","TRY", "1", "0.001"),
    
    BUSD_TRY("BUSD","TRY", "0.001", "1"),
    
    ETH_TRY("ETH","TRY", "1", "0.0001"),
    
    XRP_TRY("XRP","TRY", "0.001", "1"),
    
    USDT_TRY("USDT","TRY", "0.001", "1"),
    
    USDT_RUB("USDT","RUB", "0.01", "1"),
    
    BTC_EUR("BTC","EUR", "0.01", "0.00001"),
    
    ETH_EUR("ETH","EUR", "0.01", "0.0001"),
    
    BNB_EUR("BNB","EUR", "0.1", "0.001"),
    
    XRP_EUR("XRP","EUR", "0.0001", "1"),
    
    EUR_BUSD("EUR","BUSD", "0.001", "0.1"),
    
    EUR_USDT("EUR","USDT", "0.001", "0.1"),
    
    OGN_BNB("OGN","BNB", "0.000001", "1"),
    
    OGN_BTC("OGN","BTC", "0.00000001", "1"),
    
    OGN_USDT("OGN","USDT", "0.0001", "1"),
    
    DREP_BTC("DREP","BTC", "0.00000001", "1"),
    
    DREP_USDT("DREP","USDT", "0.0001", "1"),
    
    TCT_BTC("TCT","BTC", "0.00000001", "1"),
    
    TCT_USDT("TCT","USDT", "0.00001", "1"),
    
    WRX_BNB("WRX","BNB", "0.000001", "0.1"),
    
    WRX_BTC("WRX","BTC", "0.00000001", "0.1"),
    
    WRX_USDT("WRX","USDT", "0.001", "0.1"),
    
    ICX_BUSD("ICX","BUSD", "0.001", "0.1"),
    
    BTS_USDT("BTS","USDT", "0.00001", "0.1"),
    
    LSK_USDT("LSK","USDT", "0.001", "0.1"),
    
    BNT_USDT("BNT","USDT", "0.001", "0.1"),
    
    BNT_BUSD("BNT","BUSD", "0.001", "0.1"),
    
    LTO_BTC("LTO","BTC", "0.00000001", "1"),
    
    LTO_USDT("LTO","USDT", "0.0001", "1"),
    
    ATOM_BUSD("ATOM","BUSD", "0.01", "0.01"),
    
    DASH_BUSD("DASH","BUSD", "0.1", "0.001"),
    
    NEO_BUSD("NEO","BUSD", "0.01", "0.01"),
    
    WAVES_BUSD("WAVES","BUSD", "0.01", "0.01"),
    
    XTZ_BUSD("XTZ","BUSD", "0.001", "0.1"),
    
    BAT_BUSD("BAT","BUSD", "0.0001", "1"),
    
    ENJ_BUSD("ENJ","BUSD", "0.001", "0.1"),
    
    NANO_BUSD("NANO","BUSD", "0.001", "0.1"),
    
    ONT_BUSD("ONT","BUSD", "0.0001", "1"),
    
    RVN_BUSD("RVN","BUSD", "0.00001", "0.1"),
    
    AION_USDT("AION","USDT", "0.0001", "1"),
    
    MBL_BNB("MBL","BNB", "0.00000001", "1"),
    
    MBL_USDT("MBL","USDT", "0.000001", "1"),
    
    COTI_BNB("COTI","BNB", "0.0000001", "1"),
    
    COTI_BTC("COTI","BTC", "0.00000001", "1"),
    
    COTI_USDT("COTI","USDT", "0.0001", "1"),
    
    ALGO_BUSD("ALGO","BUSD", "0.0001", "1"),
    
    BTT_BUSD("BTT","BUSD", "0.000001", "1"),
    
    TOMO_BUSD("TOMO","BUSD", "0.001", "0.1"),
    
    XMR_BUSD("XMR","BUSD", "0.1", "0.001"),
    
    ZEC_BUSD("ZEC","BUSD", "0.1", "0.001"),
    
    STPT_BTC("STPT","BTC", "0.00000001", "1"),
    
    STPT_USDT("STPT","USDT", "0.00001", "0.1"),
    
    WTC_USDT("WTC","USDT", "0.0001", "1"),
    
    DATA_BUSD("DATA","BUSD", "0.00001", "0.1"),
    
    DATA_USDT("DATA","USDT", "0.00001", "0.1"),
    
    SOL_BNB("SOL","BNB", "0.0001", "0.01"),
    
    SOL_BTC("SOL","BTC", "0.0000001", "0.01"),
    
    SOL_USDT("SOL","USDT", "0.01", "0.01"),
    
    SOL_BUSD("SOL","BUSD", "0.01", "0.01"),
    
    BNB_IDRT("BNB","IDRT", "1", "0.001"),
    
    USDT_IDRT("USDT","IDRT", "1", "1"),
    
    CTSI_BTC("CTSI","BTC", "0.00000001", "1"),
    
    CTSI_USDT("CTSI","USDT", "0.0001", "1"),
    
    CTSI_BNB("CTSI","BNB", "0.000001", "1"),
    
    CTSI_BUSD("CTSI","BUSD", "0.0001", "1"),
    
    HIVE_BTC("HIVE","BTC", "0.00000001", "1"),
    
    HIVE_USDT("HIVE","USDT", "0.0001", "1"),
    
    CHR_BNB("CHR","BNB", "0.0000001", "1"),
    
    CHR_BTC("CHR","BTC", "0.00000001", "1"),
    
    CHR_USDT("CHR","USDT", "0.0001", "1"),
    
    BTCUP_USDT("BTCUP","USDT", "0.001", "0.01"),
    
    BTCDOWN_USDT("BTCDOWN","USDT", "0.000001", "0.01"),
    
    GXS_USDT("GXS","USDT", "0.0001", "1"),
    
    ARDR_USDT("ARDR","USDT", "0.0001", "1"),
    
    HBAR_BUSD("HBAR","BUSD", "0.0001", "1"),
    
    MATIC_BUSD("MATIC","BUSD", "0.001", "0.1"),
    
    WRX_BUSD("WRX","BUSD", "0.001", "0.1"),
    
    ZIL_BUSD("ZIL","BUSD", "0.00001", "0.1"),
    
    MDT_BTC("MDT","BTC", "0.00000001", "1"),
    
    MDT_USDT("MDT","USDT", "0.00001", "0.1"),
    
    STMX_BNB("STMX","BNB", "0.00000001", "1"),
    
    STMX_BTC("STMX","BTC", "0.00000001", "1"),
    
    STMX_ETH("STMX","ETH", "0.00000001", "1"),
    
    STMX_USDT("STMX","USDT", "0.00001", "1"),
    
    KNC_BUSD("KNC","BUSD", "0.001", "0.1"),
    
    KNC_USDT("KNC","USDT", "0.001", "0.1"),
    
    REP_USDT("REP","USDT", "0.01", "0.01"),
    
    LRC_BUSD("LRC","BUSD", "0.0001", "1"),
    
    LRC_USDT("LRC","USDT", "0.0001", "1"),
    
    IQ_BNB("IQ","BNB", "0.00000001", "1"),
    
    IQ_BUSD("IQ","BUSD", "0.00001", "1"),
    
    PNT_BTC("PNT","BTC", "0.00000001", "1"),
    
    PNT_USDT("PNT","USDT", "0.0001", "1"),
    
    BTC_GBP("BTC","GBP", "0.01", "0.00001"),
    
    ETH_GBP("ETH","GBP", "0.01", "0.0001"),
    
    XRP_GBP("XRP","GBP", "0.0001", "1"),
    
    BNB_GBP("BNB","GBP", "0.1", "0.001"),
    
    GBP_BUSD("GBP","BUSD", "0.001", "0.1"),
    
    DGB_BNB("DGB","BNB", "0.0000001", "1"),
    
    DGB_BTC("DGB","BTC", "0.00000001", "1"),
    
    DGB_BUSD("DGB","BUSD", "0.00001", "0.1"),
    
    BTC_UAH("BTC","UAH", "1", "0.00001"),
    
    USDT_UAH("USDT","UAH", "0.01", "1"),
    
    COMP_BTC("COMP","BTC", "0.00001", "0.001"),
    
    COMP_BUSD("COMP","BUSD", "0.1", "0.001"),
    
    COMP_USDT("COMP","USDT", "0.1", "0.001"),
    
    BTC_BIDR("BTC","BIDR", "1", "0.00001"),
    
    ETH_BIDR("ETH","BIDR", "1", "0.0001"),
    
    BNB_BIDR("BNB","BIDR", "1", "0.001"),
    
    BUSD_BIDR("BUSD","BIDR", "1", "0.1"),
    
    USDT_BIDR("USDT","BIDR", "1", "0.1"),
    
    SC_USDT("SC","USDT", "0.00001", "1"),
    
    ZEN_USDT("ZEN","USDT", "0.01", "0.01"),
    
    SXP_BTC("SXP","BTC", "0.00000001", "0.1"),
    
    SXP_BNB("SXP","BNB", "0.000001", "0.1"),
    
    SXP_BUSD("SXP","BUSD", "0.001", "0.1"),
    
    SNX_BTC("SNX","BTC", "0.0000001", "0.1"),
    
    SNX_BNB("SNX","BNB", "0.00001", "0.1"),
    
    SNX_BUSD("SNX","BUSD", "0.001", "0.1"),
    
    SNX_USDT("SNX","USDT", "0.001", "0.1"),
    
    ETHUP_USDT("ETHUP","USDT", "0.001", "0.01"),
    
    ETHDOWN_USDT("ETHDOWN","USDT", "0.0001", "0.01"),
    
    ADAUP_USDT("ADAUP","USDT", "0.001", "0.01"),
    
    ADADOWN_USDT("ADADOWN","USDT", "0.000001", "0.01"),
    
    LINKUP_USDT("LINKUP","USDT", "0.0001", "0.01"),
    
    LINKDOWN_USDT("LINKDOWN","USDT", "0.000001", "0.01"),
    
    VTHO_BNB("VTHO","BNB", "0.00000001", "1"),
    
    VTHO_USDT("VTHO","USDT", "0.000001", "1"),
    
    DGB_USDT("DGB","USDT", "0.00001", "0.1"),
    
    GBP_USDT("GBP","USDT", "0.001", "0.1"),
    
    STORJ_BUSD("STORJ","BUSD", "0.0001", "1"),
    
    SXP_USDT("SXP","USDT", "0.001", "0.1"),
    
    IRIS_BTC("IRIS","BTC", "0.00000001", "1"),
    
    MKR_BTC("MKR","BTC", "0.00001", "0.0001"),
    
    MKR_USDT("MKR","USDT", "1", "0.0001"),
    
    MKR_BUSD("MKR","BUSD", "1", "0.0001"),
    
    RUNE_BNB("RUNE","BNB", "0.00001", "0.1"),
    
    RUNE_BTC("RUNE","BTC", "0.0000001", "0.1"),
    
    RUNE_BUSD("RUNE","BUSD", "0.001", "0.1"),
    
    MANA_BUSD("MANA","BUSD", "0.0001", "1"),
    
    DOGE_BUSD("DOGE","BUSD", "0.0001", "1"),
    
    ZRX_BUSD("ZRX","BUSD", "0.0001", "1"),
    
    DCR_USDT("DCR","USDT", "0.1", "0.001"),
    
    STORJ_USDT("STORJ","USDT", "0.0001", "1"),
    
    BTC_AUD("BTC","AUD", "0.01", "0.00001"),
    
    ETH_AUD("ETH","AUD", "0.01", "0.0001"),
    
    AUD_BUSD("AUD","BUSD", "0.0001", "1"),
    
    FIO_BNB("FIO","BNB", "0.0000001", "1"),
    
    FIO_BTC("FIO","BTC", "0.00000001", "1"),
    
    FIO_BUSD("FIO","BUSD", "0.0001", "1"),
    
    BNBUP_USDT("BNBUP","USDT", "0.01", "0.01"),
    
    BNBDOWN_USDT("BNBDOWN","USDT", "0.00001", "0.01"),
    
    XTZUP_USDT("XTZUP","USDT", "0.00001", "0.01"),
    
    XTZDOWN_USDT("XTZDOWN","USDT", "0.001", "0.01"),
    
    AVA_BNB("AVA","BNB", "0.000001", "0.1"),
    
    AVA_BTC("AVA","BTC", "0.00000001", "0.1"),
    
    AVA_BUSD("AVA","BUSD", "0.001", "0.1"),
    
    IOTA_BUSD("IOTA","BUSD", "0.0001", "1"),
    
    MANA_USDT("MANA","USDT", "0.0001", "1"),
    
    XRP_AUD("XRP","AUD", "0.0001", "1"),
    
    BNB_AUD("BNB","AUD", "0.1", "0.001"),
    
    AUD_USDT("AUD","USDT", "0.0001", "1"),
    
    BAL_BTC("BAL","BTC", "0.0000001", "0.01"),
    
    BAL_BUSD("BAL","BUSD", "0.01", "0.01"),
    
    YFI_BTC("YFI","BTC", "0.0001", "0.00001"),
    
    YFI_BUSD("YFI","BUSD", "0.01", "0.00001"),
    
    YFI_USDT("YFI","USDT", "0.01", "0.00001"),
    
    BAL_USDT("BAL","USDT", "0.01", "0.01"),
    
    BLZ_USDT("BLZ","USDT", "0.0001", "1"),
    
    IRIS_USDT("IRIS","USDT", "0.00001", "0.1"),
    
    KMD_USDT("KMD","USDT", "0.0001", "1"),
    
    BTC_DAI("BTC","DAI", "0.01", "0.00001"),
    
    ETH_DAI("ETH","DAI", "0.01", "0.0001"),
    
    BNB_DAI("BNB","DAI", "0.1", "0.001"),
    
    USDT_DAI("USDT","DAI", "0.0001", "0.1"),
    
    BUSD_DAI("BUSD","DAI", "0.0001", "0.1"),
    
    JST_BTC("JST","BTC", "0.00000001", "1"),
    
    JST_BUSD("JST","BUSD", "0.00001", "0.1"),
    
    JST_USDT("JST","USDT", "0.00001", "0.1"),
    
    SRM_BNB("SRM","BNB", "0.00001", "0.1"),
    
    SRM_BTC("SRM","BTC", "0.0000001", "0.1"),
    
    SRM_BUSD("SRM","BUSD", "0.001", "0.1"),
    
    SRM_USDT("SRM","USDT", "0.001", "0.1"),
    
    ANT_BNB("ANT","BNB", "0.00001", "0.1"),
    
    ANT_BTC("ANT","BTC", "0.00000001", "0.1"),
    
    ANT_BUSD("ANT","BUSD", "0.001", "0.1"),
    
    ANT_USDT("ANT","USDT", "0.001", "0.1"),
    
    CRV_BTC("CRV","BTC", "0.00000001", "0.1"),
    
    CRV_BUSD("CRV","BUSD", "0.001", "0.1"),
    
    CRV_USDT("CRV","USDT", "0.001", "0.1"),
    
    SAND_BNB("SAND","BNB", "0.000001", "1"),
    
    SAND_BTC("SAND","BTC", "0.00000001", "1"),
    
    SAND_USDT("SAND","USDT", "0.0001", "1"),
    
    SAND_BUSD("SAND","BUSD", "0.0001", "1"),
    
    OCEAN_BNB("OCEAN","BNB", "0.000001", "1"),
    
    OCEAN_BTC("OCEAN","BTC", "0.00000001", "1"),
    
    OCEAN_BUSD("OCEAN","BUSD", "0.0001", "1"),
    
    OCEAN_USDT("OCEAN","USDT", "0.0001", "1"),
    
    NMR_BNB("NMR","BNB", "0.0001", "0.01"),
    
    NMR_BTC("NMR","BTC", "0.000001", "0.01"),
    
    NMR_BUSD("NMR","BUSD", "0.01", "0.01"),
    
    NMR_USDT("NMR","USDT", "0.01", "0.01"),
    
    DOT_BNB("DOT","BNB", "0.00001", "0.01"),
    
    DOT_BTC("DOT","BTC", "0.0000001", "0.01"),
    
    DOT_BUSD("DOT","BUSD", "0.01", "0.01"),
    
    DOT_USDT("DOT","USDT", "0.01", "0.01"),
    
    LUNA_BNB("LUNA","BNB", "0.00001", "0.01"),
    
    LUNA_BTC("LUNA","BTC", "0.0000001", "0.01"),
    
    LUNA_BUSD("LUNA","BUSD", "0.01", "0.01"),
    
    LUNA_USDT("LUNA","USDT", "0.01", "0.01"),
    
    IDEX_BTC("IDEX","BTC", "0.00000001", "1"),
    
    IDEX_BUSD("IDEX","BUSD", "0.00001", "0.1"),
    
    RSR_BNB("RSR","BNB", "0.0000001", "1"),
    
    RSR_BTC("RSR","BTC", "0.00000001", "1"),
    
    RSR_BUSD("RSR","BUSD", "0.00001", "0.1"),
    
    RSR_USDT("RSR","USDT", "0.00001", "0.1"),
    
    PAXG_BNB("PAXG","BNB", "0.001", "0.0001"),
    
    PAXG_BTC("PAXG","BTC", "0.00001", "0.0001"),
    
    PAXG_USDT("PAXG","USDT", "1", "0.0001"),
    
    WNXM_BTC("WNXM","BTC", "0.000001", "0.01"),
    
    WNXM_USDT("WNXM","USDT", "0.01", "0.01"),
    
    TRB_BTC("TRB","BTC", "0.000001", "0.01"),
    
    TRB_BUSD("TRB","BUSD", "0.01", "0.01"),
    
    TRB_USDT("TRB","USDT", "0.01", "0.01"),
    
    DOT_BIDR("DOT","BIDR", "1", "0.01"),
    
    LINK_AUD("LINK","AUD", "0.01", "0.01"),
    
    SXP_AUD("SXP","AUD", "0.001", "0.1"),
    
    WBTC_BTC("WBTC","BTC", "0.0001", "0.00001"),
    
    WBTC_ETH("WBTC","ETH", "0.01", "0.00001"),
    
    SUSHI_BNB("SUSHI","BNB", "0.00001", "0.1"),
    
    SUSHI_BTC("SUSHI","BTC", "0.0000001", "0.1"),
    
    SUSHI_BUSD("SUSHI","BUSD", "0.001", "0.1"),
    
    SUSHI_USDT("SUSHI","USDT", "0.001", "0.1"),
    
    YFII_BNB("YFII","BNB", "0.01", "0.0001"),
    
    YFII_BTC("YFII","BTC", "0.0001", "0.0001"),
    
    YFII_BUSD("YFII","BUSD", "1", "0.0001"),
    
    YFII_USDT("YFII","USDT", "1", "0.0001"),
    
    KSM_BNB("KSM","BNB", "0.0001", "0.001"),
    
    KSM_BTC("KSM","BTC", "0.000001", "0.001"),
    
    KSM_BUSD("KSM","BUSD", "0.1", "0.001"),
    
    KSM_USDT("KSM","USDT", "0.1", "0.001"),
    
    EGLD_BNB("EGLD","BNB", "0.0001", "0.01"),
    
    EGLD_BTC("EGLD","BTC", "0.000001", "0.01"),
    
    EGLD_BUSD("EGLD","BUSD", "0.01", "0.01"),
    
    EGLD_USDT("EGLD","USDT", "0.01", "0.01"),
    
    DIA_BTC("DIA","BTC", "0.00000001", "0.1"),
    
    DIA_BUSD("DIA","BUSD", "0.001", "0.1"),
    
    DIA_USDT("DIA","USDT", "0.001", "0.1"),
    
    RUNE_USDT("RUNE","USDT", "0.001", "0.1"),
    
    FIO_USDT("FIO","USDT", "0.0001", "1"),
    
    UMA_BTC("UMA","BTC", "0.0000001", "0.1"),
    
    UMA_USDT("UMA","USDT", "0.001", "0.1"),
    
    TRXUP_USDT("TRXUP","USDT", "0.00001", "0.01"),
    
    TRXDOWN_USDT("TRXDOWN","USDT", "0.001", "0.01"),
    
    XRPUP_USDT("XRPUP","USDT", "0.0001", "0.01"),
    
    XRPDOWN_USDT("XRPDOWN","USDT", "0.0000001", "0.01"),
    
    DOTUP_USDT("DOTUP","USDT", "0.001", "0.01"),
    
    DOTDOWN_USDT("DOTDOWN","USDT", "0.001", "0.01"),
    
    LINK_TRY("LINK","TRY", "0.1", "0.01"),
    
    USDT_NGN("USDT","NGN", "0.1", "0.1"),
    
    BEL_BNB("BEL","BNB", "0.000001", "0.1"),
    
    BEL_BTC("BEL","BTC", "0.00000001", "0.1"),
    
    BEL_BUSD("BEL","BUSD", "0.001", "0.1"),
    
    BEL_USDT("BEL","USDT", "0.001", "0.1"),
    
    WING_BTC("WING","BTC", "0.0000001", "0.01"),
    
    WING_BUSD("WING","BUSD", "0.01", "0.01"),
    
    WING_USDT("WING","USDT", "0.01", "0.01"),
    
    LTCUP_USDT("LTCUP","USDT", "0.001", "0.01"),
    
    LTCDOWN_USDT("LTCDOWN","USDT", "0.0001", "0.01"),
    
    SXP_EUR("SXP","EUR", "0.001", "0.1"),
    
    CREAM_BNB("CREAM","BNB", "0.0001", "0.001"),
    
    CREAM_BUSD("CREAM","BUSD", "0.1", "0.001"),
    
    UNI_BNB("UNI","BNB", "0.00001", "0.01"),
    
    UNI_BTC("UNI","BTC", "0.0000001", "0.01"),
    
    UNI_BUSD("UNI","BUSD", "0.01", "0.01"),
    
    UNI_USDT("UNI","USDT", "0.01", "0.01"),
    
    NBS_USDT("NBS","USDT", "0.00001", "0.1"),
    
    OXT_BTC("OXT","BTC", "0.00000001", "1"),
    
    OXT_USDT("OXT","USDT", "0.0001", "1"),
    
    SUN_USDT("SUN","USDT", "0.00001", "1"),
    
    AVAX_BNB("AVAX","BNB", "0.00001", "0.01"),
    
    AVAX_BTC("AVAX","BTC", "0.0000001", "0.01"),
    
    AVAX_BUSD("AVAX","BUSD", "0.01", "0.01"),
    
    AVAX_USDT("AVAX","USDT", "0.01", "0.01"),
    
    HNT_BTC("HNT","BTC", "0.0000001", "0.01"),
    
    HNT_USDT("HNT","USDT", "0.01", "0.01"),
    
    BAKE_BNB("BAKE","BNB", "0.000001", "0.1"),
    
    BURGER_BNB("BURGER","BNB", "0.00001", "0.1"),
    
    SXP_BIDR("SXP","BIDR", "1", "0.1"),
    
    FLM_BTC("FLM","BTC", "0.00000001", "1"),
    
    FLM_BUSD("FLM","BUSD", "0.0001", "1"),
    
    FLM_USDT("FLM","USDT", "0.0001", "1"),
    
    SCRT_BTC("SCRT","BTC", "0.00000001", "0.1"),
    
    SCRT_ETH("SCRT","ETH", "0.0000001", "1"),
    
    CAKE_BNB("CAKE","BNB", "0.00001", "0.01"),
    
    CAKE_BUSD("CAKE","BUSD", "0.01", "0.01"),
    
    SPARTA_BNB("SPARTA","BNB", "0.0000001", "1"),
    
    ORN_BTC("ORN","BTC", "0.0000001", "0.1"),
    
    ORN_USDT("ORN","USDT", "0.001", "0.1"),
    
    SXP_TRY("SXP","TRY", "0.01", "0.1"),
    
    UTK_BTC("UTK","BTC", "0.00000001", "1"),
    
    UTK_USDT("UTK","USDT", "0.0001", "1"),
    
    XVS_BNB("XVS","BNB", "0.00001", "0.01"),
    
    XVS_BTC("XVS","BTC", "0.0000001", "0.01"),
    
    XVS_BUSD("XVS","BUSD", "0.01", "0.01"),
    
    XVS_USDT("XVS","USDT", "0.01", "0.01"),
    
    ALPHA_BNB("ALPHA","BNB", "0.000001", "1"),
    
    ALPHA_BTC("ALPHA","BTC", "0.00000001", "1"),
    
    ALPHA_BUSD("ALPHA","BUSD", "0.0001", "1"),
    
    ALPHA_USDT("ALPHA","USDT", "0.0001", "1"),
    
    VIDT_BTC("VIDT","BTC", "0.00000001", "1"),
    
    VIDT_BUSD("VIDT","BUSD", "0.0001", "1"),
    
    AAVE_BNB("AAVE","BNB", "0.0001", "0.001"),
    
    BTC_BRL("BTC","BRL", "1", "0.00001"),
    
    USDT_BRL("USDT","BRL", "0.001", "0.1"),
    
    AAVE_BTC("AAVE","BTC", "0.000001", "0.001"),
    
    AAVE_ETH("AAVE","ETH", "0.0001", "0.001"),
    
    AAVE_BUSD("AAVE","BUSD", "0.1", "0.001"),
    
    AAVE_USDT("AAVE","USDT", "0.1", "0.001"),
    
    NEAR_BNB("NEAR","BNB", "0.000001", "0.1"),
    
    NEAR_BTC("NEAR","BTC", "0.00000001", "0.1"),
    
    NEAR_BUSD("NEAR","BUSD", "0.001", "0.1"),
    
    NEAR_USDT("NEAR","USDT", "0.001", "0.1"),
    
    SXP_GBP("SXP","GBP", "0.001", "0.1"),
    
    FIL_BNB("FIL","BNB", "0.0001", "0.01"),
    
    FIL_BTC("FIL","BTC", "0.000001", "0.01"),
    
    FIL_BUSD("FIL","BUSD", "0.01", "0.01"),
    
    FIL_USDT("FIL","USDT", "0.01", "0.01"),
    
    INJ_BNB("INJ","BNB", "0.00001", "0.1"),
    
    INJ_BTC("INJ","BTC", "0.0000001", "0.1"),
    
    INJ_BUSD("INJ","BUSD", "0.001", "0.1"),
    
    INJ_USDT("INJ","USDT", "0.001", "0.1"),
    
    AERGO_BTC("AERGO","BTC", "0.00000001", "1"),
    
    AERGO_BUSD("AERGO","BUSD", "0.0001", "1"),
    
    LINK_EUR("LINK","EUR", "0.01", "0.01"),
    
    ONE_BUSD("ONE","BUSD", "0.00001", "0.1"),
    
    AUDIO_BTC("AUDIO","BTC", "0.00000001", "0.1"),
    
    AUDIO_BUSD("AUDIO","BUSD", "0.001", "0.1"),
    
    AUDIO_USDT("AUDIO","USDT", "0.001", "0.1"),
    
    CTK_BNB("CTK","BNB", "0.000001", "0.1"),
    
    CTK_BTC("CTK","BTC", "0.00000001", "0.1"),
    
    CTK_BUSD("CTK","BUSD", "0.001", "0.1"),
    
    CTK_USDT("CTK","USDT", "0.001", "0.1"),
    
    ETH_BRL("ETH","BRL", "0.01", "0.0001"),
    
    DOT_EUR("DOT","EUR", "0.01", "0.01"),
    
    AKRO_BTC("AKRO","BTC", "0.00000001", "1"),
    
    AKRO_USDT("AKRO","USDT", "0.00001", "1"),
    
    KP3R_BNB("KP3R","BNB", "0.0001", "0.01"),
    
    KP3R_BUSD("KP3R","BUSD", "0.01", "0.01"),
    
    AXS_BNB("AXS","BNB", "0.0001", "0.01"),
    
    AXS_BTC("AXS","BTC", "0.000001", "0.01"),
    
    AXS_BUSD("AXS","BUSD", "0.01", "0.01"),
    
    AXS_USDT("AXS","USDT", "0.01", "0.01"),
    
    HARD_BNB("HARD","BNB", "0.000001", "1"),
    
    HARD_BTC("HARD","BTC", "0.00000001", "1"),
    
    HARD_BUSD("HARD","BUSD", "0.0001", "1"),
    
    HARD_USDT("HARD","USDT", "0.0001", "1"),
    
    BNB_BRL("BNB","BRL", "1", "0.001"),
    
    LTC_EUR("LTC","EUR", "0.1", "0.001"),
    
    RENBTC_BTC("RENBTC","BTC", "0.0001", "0.00001"),
    
    DNT_BUSD("DNT","BUSD", "0.0001", "1"),
    
    DNT_USDT("DNT","USDT", "0.0001", "1"),
    
    SLP_ETH("SLP","ETH", "0.00000001", "1"),
    
    ADA_EUR("ADA","EUR", "0.001", "0.1"),
    
    CVP_ETH("CVP","ETH", "0.0000001", "1"),
    
    CVP_BUSD("CVP","BUSD", "0.001", "0.1"),
    
    STRAX_BTC("STRAX","BTC", "0.00000001", "0.1"),
    
    STRAX_ETH("STRAX","ETH", "0.0000001", "0.1"),
    
    STRAX_BUSD("STRAX","BUSD", "0.001", "0.1"),
    
    STRAX_USDT("STRAX","USDT", "0.001", "0.1"),
    
    FOR_BTC("FOR","BTC", "0.00000001", "1"),
    
    FOR_BUSD("FOR","BUSD", "0.00001", "0.1"),
    
    UNFI_BTC("UNFI","BTC", "0.0000001", "0.1"),
    
    UNFI_BUSD("UNFI","BUSD", "0.001", "0.1"),
    
    UNFI_USDT("UNFI","USDT", "0.001", "0.1"),
    
    FRONT_BUSD("FRONT","BUSD", "0.0001", "1"),
    
    ROSE_BTC("ROSE","BTC", "0.00000001", "1"),
    
    ROSE_BUSD("ROSE","BUSD", "0.00001", "0.1"),
    
    ROSE_USDT("ROSE","USDT", "0.00001", "0.1"),
    
    AVAX_TRY("AVAX","TRY", "0.1", "0.01"),
    
    BUSD_BRL("BUSD","BRL", "0.001", "0.1"),
    
    AVA_USDT("AVA","USDT", "0.001", "0.1"),
    
    SYS_BUSD("SYS","BUSD", "0.0001", "1"),
    
    XEM_USDT("XEM","USDT", "0.0001", "1"),
    
    HEGIC_ETH("HEGIC","ETH", "0.00000001", "1"),
    
    HEGIC_BUSD("HEGIC","BUSD", "0.0001", "1"),
    
    PROM_BNB("PROM","BNB", "0.00001", "0.01"),
    
    PROM_BUSD("PROM","BUSD", "0.01", "0.01"),
    
    XRP_BRL("XRP","BRL", "0.001", "1"),
    
    SKL_BTC("SKL","BTC", "0.00000001", "1"),
    
    SKL_BUSD("SKL","BUSD", "0.0001", "1"),
    
    SKL_USDT("SKL","USDT", "0.0001", "1"),
    
    BCH_EUR("BCH","EUR", "0.1", "0.001"),
    
    YFI_EUR("YFI","EUR", "0.01", "0.00001"),
    
    ZIL_BIDR("ZIL","BIDR", "1", "1"),
    
    SUSD_USDT("SUSD","USDT", "0.001", "0.1"),
    
    GLM_BTC("GLM","BTC", "0.00000001", "1"),
    
    GLM_ETH("GLM","ETH", "0.0000001", "1"),
    
    GHST_ETH("GHST","ETH", "0.0000001", "0.1"),
    
    GHST_BUSD("GHST","BUSD", "0.001", "0.1"),
    
    LINK_BRL("LINK","BRL", "0.1", "0.01"),
    
    LTC_RUB("LTC","RUB", "0.1", "0.001"),
    
    TRX_TRY("TRX","TRY", "0.0001", "1"),
    
    XLM_EUR("XLM","EUR", "0.0001", "1"),
    
    DF_BUSD("DF","BUSD", "0.0001", "1"),
    
    GRT_BTC("GRT","BTC", "0.00000001", "1"),
    
    GRT_ETH("GRT","ETH", "0.0000001", "1"),
    
    GRT_USDT("GRT","USDT", "0.0001", "1"),
    
    JUV_BTC("JUV","BTC", "0.0000001", "0.01"),
    
    JUV_BUSD("JUV","BUSD", "0.01", "0.01"),
    
    JUV_USDT("JUV","USDT", "0.01", "0.01"),
    
    PSG_BTC("PSG","BTC", "0.0000001", "0.01"),
    
    PSG_BUSD("PSG","BUSD", "0.01", "0.01"),
    
    PSG_USDT("PSG","USDT", "0.01", "0.01"),
    
    $1INCH_BTC("1INCH","BTC", "0.00000001", "0.1"),
    
    $1INCH_USDT("1INCH","USDT", "0.001", "0.1"),
    
    REEF_BTC("REEF","BTC", "0.00000001", "1"),
    
    REEF_USDT("REEF","USDT", "0.00001", "1"),
    
    OG_BTC("OG","BTC", "0.0000001", "0.1"),
    
    OG_USDT("OG","USDT", "0.001", "0.1"),
    
    ATM_BTC("ATM","BTC", "0.0000001", "0.01"),
    
    ATM_USDT("ATM","USDT", "0.01", "0.01"),
    
    ASR_BTC("ASR","BTC", "0.0000001", "0.1"),
    
    ASR_USDT("ASR","USDT", "0.001", "0.1"),
    
    CELO_BTC("CELO","BTC", "0.00000001", "0.1"),
    
    CELO_USDT("CELO","USDT", "0.001", "0.1"),
    
    RIF_BTC("RIF","BTC", "0.00000001", "1"),
    
    RIF_USDT("RIF","USDT", "0.0001", "1"),
    
    CHZ_TRY("CHZ","TRY", "0.001", "1"),
    
    XLM_TRY("XLM","TRY", "0.001", "1"),
    
    LINK_GBP("LINK","GBP", "0.01", "0.01"),
    
    GRT_EUR("GRT","EUR", "0.0001", "1"),
    
    BTCST_BTC("BTCST","BTC", "0.0000001", "0.01"),
    
    BTCST_BUSD("BTCST","BUSD", "0.01", "0.01"),
    
    BTCST_USDT("BTCST","USDT", "0.01", "0.01"),
    
    TRU_BTC("TRU","BTC", "0.00000001", "1"),
    
    TRU_USDT("TRU","USDT", "0.0001", "1"),
    
    DEXE_ETH("DEXE","ETH", "0.000001", "0.01"),
    
    DEXE_BUSD("DEXE","BUSD", "0.01", "0.01"),
    
    EOS_EUR("EOS","EUR", "0.001", "0.1"),
    
    LTC_BRL("LTC","BRL", "0.1", "0.001"),
    
    USDC_BUSD("USDC","BUSD", "0.0001", "0.01"),
    
    TUSD_BUSD("TUSD","BUSD", "0.0001", "0.1"),
    
    CKB_BTC("CKB","BTC", "0.00000001", "1"),
    
    CKB_BUSD("CKB","BUSD", "0.00001", "1"),
    
    CKB_USDT("CKB","USDT", "0.00001", "1"),
    
    TWT_BTC("TWT","BTC", "0.00000001", "1"),
    
    TWT_BUSD("TWT","BUSD", "0.0001", "1"),
    
    TWT_USDT("TWT","USDT", "0.0001", "1"),
    
    FIRO_BTC("FIRO","BTC", "0.0000001", "0.1"),
    
    FIRO_ETH("FIRO","ETH", "0.000001", "0.1"),
    
    FIRO_USDT("FIRO","USDT", "0.001", "0.1"),
    
    BETH_ETH("BETH","ETH", "0.0001", "0.0001"),
    
    DOGE_EUR("DOGE","EUR", "0.0001", "1"),
    
    DOGE_TRY("DOGE","TRY", "0.001", "1"),
    
    DOGE_AUD("DOGE","AUD", "0.0001", "1"),
    
    DOGE_BRL("DOGE","BRL", "0.001", "1"),
    
    PROS_ETH("PROS","ETH", "0.0000001", "1"),
    
    LIT_BTC("LIT","BTC", "0.00000001", "0.1"),
    
    LIT_BUSD("LIT","BUSD", "0.001", "0.1"),
    
    LIT_USDT("LIT","USDT", "0.001", "0.1"),
    
    BUSD_VAI("BUSD","VAI", "0.001", "0.1"),
    
    SFP_BTC("SFP","BTC", "0.00000001", "1"),
    
    SFP_BUSD("SFP","BUSD", "0.0001", "1"),
    
    SFP_USDT("SFP","USDT", "0.0001", "1"),
    
    DOGE_GBP("DOGE","GBP", "0.0001", "1"),
    
    DOT_TRY("DOT","TRY", "0.1", "0.01"),
    
    FXS_BTC("FXS","BTC", "0.00000001", "0.1"),
    
    FXS_BUSD("FXS","BUSD", "0.001", "0.1"),
    
    DODO_BTC("DODO","BTC", "0.00000001", "0.1"),
    
    DODO_BUSD("DODO","BUSD", "0.001", "0.1"),
    
    DODO_USDT("DODO","USDT", "0.001", "0.1"),
    
    FRONT_BTC("FRONT","BTC", "0.00000001", "1"),
    
    CAKE_BTC("CAKE","BTC", "0.0000001", "0.01"),
    
    CAKE_USDT("CAKE","USDT", "0.01", "0.01"),
    
    BAKE_BUSD("BAKE","BUSD", "0.001", "0.1"),
    
    UFT_ETH("UFT","ETH", "0.0000001", "1"),
    
    UFT_BUSD("UFT","BUSD", "0.0001", "1"),
    
    $1INCH_BUSD("1INCH","BUSD", "0.001", "0.1"),
    
    BAND_BUSD("BAND","BUSD", "0.001", "0.1"),
    
    GRT_BUSD("GRT","BUSD", "0.0001", "1"),
    
    IOST_BUSD("IOST","BUSD", "0.00001", "0.1"),
    
    OMG_BUSD("OMG","BUSD", "0.001", "0.1"),
    
    REEF_BUSD("REEF","BUSD", "0.00001", "0.1"),
    
    ACM_BTC("ACM","BTC", "0.0000001", "0.1"),
    
    ACM_BUSD("ACM","BUSD", "0.001", "0.1"),
    
    ACM_USDT("ACM","USDT", "0.001", "0.1"),
    
    AUCTION_BTC("AUCTION","BTC", "0.0000001", "0.01"),
    
    AUCTION_BUSD("AUCTION","BUSD", "0.01", "0.01"),
    
    PHA_BTC("PHA","BTC", "0.00000001", "1"),
    
    PHA_BUSD("PHA","BUSD", "0.0001", "1"),
    
    DOT_GBP("DOT","GBP", "0.01", "0.01"),
    
    ADA_TRY("ADA","TRY", "0.01", "0.1"),
    
    ADA_BRL("ADA","BRL", "0.001", "0.1"),
    
    ADA_GBP("ADA","GBP", "0.0001", "0.1"),
    
    TVK_BTC("TVK","BTC", "0.00000001", "1"),
    
    TVK_BUSD("TVK","BUSD", "0.0001", "1"),
    
    BADGER_BTC("BADGER","BTC", "0.0000001", "0.01"),
    
    BADGER_BUSD("BADGER","BUSD", "0.01", "0.01"),
    
    BADGER_USDT("BADGER","USDT", "0.01", "0.01"),
    
    FIS_BTC("FIS","BTC", "0.00000001", "1"),
    
    FIS_BUSD("FIS","BUSD", "0.0001", "1"),
    
    FIS_USDT("FIS","USDT", "0.0001", "1"),
    
    DOT_BRL("DOT","BRL", "0.01", "0.01"),
    
    ADA_AUD("ADA","AUD", "0.001", "0.1"),
    
    HOT_TRY("HOT","TRY", "0.00001", "1"),
    
    EGLD_EUR("EGLD","EUR", "0.01", "0.01"),
    
    OM_BTC("OM","BTC", "0.00000001", "1"),
    
    OM_BUSD("OM","BUSD", "0.0001", "1"),
    
    OM_USDT("OM","USDT", "0.0001", "1"),
    
    POND_BTC("POND","BTC", "0.00000001", "1"),
    
    POND_BUSD("POND","BUSD", "0.00001", "0.01"),
    
    POND_USDT("POND","USDT", "0.00001", "0.01"),
    
    DEGO_BTC("DEGO","BTC", "0.0000001", "0.01"),
    
    DEGO_BUSD("DEGO","BUSD", "0.01", "0.01"),
    
    DEGO_USDT("DEGO","USDT", "0.01", "0.01"),
    
    AVAX_EUR("AVAX","EUR", "0.01", "0.01"),
    
    BTT_TRY("BTT","TRY", "0.00001", "1"),
    
    CHZ_BRL("CHZ","BRL", "0.001", "1"),
    
    UNI_EUR("UNI","EUR", "0.01", "0.01"),
    
    ALICE_BTC("ALICE","BTC", "0.0000001", "0.01"),
    
    ALICE_BUSD("ALICE","BUSD", "0.01", "0.01"),
    
    ALICE_USDT("ALICE","USDT", "0.01", "0.01"),
    
    CHZ_BUSD("CHZ","BUSD", "0.0001", "1"),
    
    CHZ_EUR("CHZ","EUR", "0.0001", "1"),
    
    CHZ_GBP("CHZ","GBP", "0.0001", "1"),
    
    BIFI_BUSD("BIFI","BUSD", "0.1", "0.001"),
    
    LINA_BTC("LINA","BTC", "0.00000001", "1"),
    
    LINA_BUSD("LINA","BUSD", "0.00001", "0.01"),
    
    LINA_USDT("LINA","USDT", "0.00001", "0.01"),
    
    ADA_RUB("ADA","RUB", "0.01", "0.1"),
    
    ENJ_BRL("ENJ","BRL", "0.001", "0.1"),
    
    ENJ_EUR("ENJ","EUR", "0.001", "0.1"),
    
    MATIC_EUR("MATIC","EUR", "0.0001", "0.1"),
    
    NEO_TRY("NEO","TRY", "0.1", "0.01"),
    
    PERP_BTC("PERP","BTC", "0.0000001", "0.01"),
    
    PERP_BUSD("PERP","BUSD", "0.01", "0.01"),
    
    PERP_USDT("PERP","USDT", "0.01", "0.01"),
    
    RAMP_BTC("RAMP","BTC", "0.00000001", "1"),
    
    RAMP_BUSD("RAMP","BUSD", "0.0001", "1"),
    
    RAMP_USDT("RAMP","USDT", "0.0001", "1"),
    
    SUPER_BTC("SUPER","BTC", "0.00000001", "1"),
    
    SUPER_BUSD("SUPER","BUSD", "0.0001", "1"),
    
    SUPER_USDT("SUPER","USDT", "0.0001", "1"),
    
    CFX_BTC("CFX","BTC", "0.00000001", "1"),
    
    CFX_BUSD("CFX","BUSD", "0.0001", "1"),
    
    CFX_USDT("CFX","USDT", "0.0001", "1"),
    
    ENJ_GBP("ENJ","GBP", "0.0001", "0.1"),
    
    EOS_TRY("EOS","TRY", "0.01", "0.1"),
    
    LTC_GBP("LTC","GBP", "0.01", "0.001"),
    
    LUNA_EUR("LUNA","EUR", "0.01", "0.01"),
    
    RVN_TRY("RVN","TRY", "0.0001", "0.01"),
    
    THETA_EUR("THETA","EUR", "0.001", "0.1"),
    
    XVG_BUSD("XVG","BUSD", "0.00001", "0.1"),
    
    EPS_BTC("EPS","BTC", "0.00000001", "1"),
    
    EPS_BUSD("EPS","BUSD", "0.0001", "1"),
    
    EPS_USDT("EPS","USDT", "0.0001", "1"),
    
    AUTO_BTC("AUTO","BTC", "0.00001", "0.001"),
    
    AUTO_BUSD("AUTO","BUSD", "0.1", "0.001"),
    
    AUTO_USDT("AUTO","USDT", "0.1", "0.001"),
    
    TKO_BTC("TKO","BTC", "0.00000001", "0.1"),
    
    TKO_BIDR("TKO","BIDR", "0.01", "0.1"),
    
    TKO_BUSD("TKO","BUSD", "0.001", "0.1"),
    
    TKO_USDT("TKO","USDT", "0.001", "0.1"),
    
    PUNDIX_ETH("PUNDIX","ETH", "0.0000001", "0.1"),
    
    PUNDIX_USDT("PUNDIX","USDT", "0.001", "0.1"),
    
    BTT_BRL("BTT","BRL", "0.00001", "0.1"),
    
    BTT_EUR("BTT","EUR", "0.000001", "1"),
    
    HOT_EUR("HOT","EUR", "0.000001", "0.1"),
    
    WIN_EUR("WIN","EUR", "0.0000001", "1"),
    
    TLM_BTC("TLM","BTC", "0.00000001", "1"),
    
    TLM_BUSD("TLM","BUSD", "0.0001", "1"),
    
    TLM_USDT("TLM","USDT", "0.0001", "1"),
    
    BTG_BUSD("BTG","BUSD", "0.01", "0.01"),
    
    BTG_USDT("BTG","USDT", "0.01", "0.01"),
    
    HOT_BUSD("HOT","BUSD", "0.000001", "0.1"),
    
    BNB_UAH("BNB","UAH", "1", "0.001"),
    
    ONT_TRY("ONT","TRY", "0.001", "1"),
    
    VET_EUR("VET","EUR", "0.00001", "0.01"),
    
    VET_GBP("VET","GBP", "0.00001", "0.01"),
    
    WIN_BRL("WIN","BRL", "0.000001", "1"),
    
    MIR_BTC("MIR","BTC", "0.00000001", "0.1"),
    
    MIR_BUSD("MIR","BUSD", "0.001", "0.1"),
    
    MIR_USDT("MIR","USDT", "0.001", "0.1"),
    
    BAR_BTC("BAR","BTC", "0.0000001", "0.01"),
    
    BAR_BUSD("BAR","BUSD", "0.01", "0.01"),
    
    BAR_USDT("BAR","USDT", "0.01", "0.01"),
    
    FORTH_BTC("FORTH","BTC", "0.0000001", "0.01"),
    
    FORTH_BUSD("FORTH","BUSD", "0.01", "0.01"),
    
    FORTH_USDT("FORTH","USDT", "0.01", "0.01"),
    
    CAKE_GBP("CAKE","GBP", "0.01", "0.01"),
    
    DOGE_RUB("DOGE","RUB", "0.01", "1"),
    
    WRX_EUR("WRX","EUR", "0.0001", "0.1"),
    
    EZ_BTC("EZ","BTC", "0.00000001", "0.1"),
    
    EZ_ETH("EZ","ETH", "0.000001", "0.1"),
    
    BAKE_USDT("BAKE","USDT", "0.001", "0.1"),
    
    BURGER_BUSD("BURGER","BUSD", "0.001", "0.1"),
    
    BURGER_USDT("BURGER","USDT", "0.001", "0.1"),
    
    SLP_BUSD("SLP","BUSD", "0.0001", "1"),
    
    SLP_USDT("SLP","USDT", "0.0001", "1"),
    
    TRX_EUR("TRX","EUR", "0.00001", "1"),
    
    VET_TRY("VET","TRY", "0.0001", "0.1"),
    
    SHIB_USDT("SHIB","USDT", "0.00000001", "1"),
    
    SHIB_BUSD("SHIB","BUSD", "0.00000001", "1"),
    
    ICP_BTC("ICP","BTC", "0.000001", "0.01"),
    
    ICP_BNB("ICP","BNB", "0.0001", "0.01"),
    
    ICP_BUSD("ICP","BUSD", "0.01", "0.01"),
    
    ICP_USDT("ICP","USDT", "0.01", "0.01"),
    
    SHIB_EUR("SHIB","EUR", "0.00000001", "1"),
    
    ETC_EUR("ETC","EUR", "0.01", "0.01"),
    
    DOGE_BIDR("DOGE","BIDR", "1", "1"),
    
    AR_BTC("AR","BTC", "0.0000001", "0.01"),
    
    AR_BNB("AR","BNB", "0.00001", "0.01"),
    
    AR_BUSD("AR","BUSD", "0.01", "0.01"),
    
    AR_USDT("AR","USDT", "0.01", "0.01"),
    
    POLS_BTC("POLS","BTC", "0.00000001", "0.1"),
    
    POLS_BNB("POLS","BNB", "0.000001", "0.1"),
    
    POLS_BUSD("POLS","BUSD", "0.001", "0.1"),
    
    POLS_USDT("POLS","USDT", "0.001", "0.1"),
    
    MDX_BTC("MDX","BTC", "0.00000001", "0.1"),
    
    MDX_BNB("MDX","BNB", "0.000001", "0.1"),
    
    MDX_BUSD("MDX","BUSD", "0.001", "0.1"),
    
    MDX_USDT("MDX","USDT", "0.001", "0.1"),
    
    MASK_BNB("MASK","BNB", "0.00001", "0.1"),
    
    MASK_BUSD("MASK","BUSD", "0.001", "0.1"),
    
    MASK_USDT("MASK","USDT", "0.001", "0.1"),
    
    LPT_BTC("LPT","BTC", "0.0000001", "0.01"),
    
    LPT_BNB("LPT","BNB", "0.00001", "0.01"),
    
    LPT_BUSD("LPT","BUSD", "0.01", "0.01"),
    
    LPT_USDT("LPT","USDT", "0.01", "0.01"),
    
    ETH_UAH("ETH","UAH", "1", "0.0001"),
    
    MATIC_BRL("MATIC","BRL", "0.001", "0.1"),
    
    SOL_EUR("SOL","EUR", "0.01", "0.01"),
    
    SHIB_BRL("SHIB","BRL", "0.00000001", "1"),
    
    AGIX_BTC("AGIX","BTC", "0.00000001", "1"),
    
    ICP_EUR("ICP","EUR", "0.01", "0.01"),
    
    MATIC_GBP("MATIC","GBP", "0.0001", "0.1"),
    
    SHIB_TRY("SHIB","TRY", "0.00000001", "1"),
    
    MATIC_BIDR("MATIC","BIDR", "1", "0.1"),
    
    MATIC_RUB("MATIC","RUB", "0.01", "0.1"),
    
    NU_BTC("NU","BTC", "0.00000001", "1"),
    
    NU_BNB("NU","BNB", "0.0000001", "1"),
    
    NU_BUSD("NU","BUSD", "0.0001", "1"),
    
    NU_USDT("NU","USDT", "0.0001", "1"),
    
    XVG_USDT("XVG","USDT", "0.00001", "1"),
    
    RLC_BUSD("RLC","BUSD", "0.001", "0.1"),
    
    CELR_BUSD("CELR","BUSD", "0.00001", "1"),
    
    ATM_BUSD("ATM","BUSD", "0.01", "0.01"),
    
    ZEN_BUSD("ZEN","BUSD", "0.01", "0.01"),
    
    FTM_BUSD("FTM","BUSD", "0.0001", "1"),
    
    THETA_BUSD("THETA","BUSD", "0.001", "0.1"),
    
    WIN_BUSD("WIN","BUSD", "0.0000001", "1"),
    
    KAVA_BUSD("KAVA","BUSD", "0.001", "0.1"),
    
    XEM_BUSD("XEM","BUSD", "0.0001", "1"),
    
    ATA_BTC("ATA","BTC", "0.00000001", "1"),
    
    ATA_BNB("ATA","BNB", "0.000001", "1"),
    
    ATA_BUSD("ATA","BUSD", "0.0001", "1"),
    
    ATA_USDT("ATA","USDT", "0.0001", "1"),
    
    GTC_BTC("GTC","BTC", "0.0000001", "0.1"),
    
    GTC_BUSD("GTC","BUSD", "0.001", "0.1"),
    
    GTC_USDT("GTC","USDT", "0.001", "0.1"),
    
    TORN_BTC("TORN","BTC", "0.0000001", "0.01"),
    
    TORN_BNB("TORN","BNB", "0.00001", "0.01"),
    
    TORN_BUSD("TORN","BUSD", "0.01", "0.01"),
    
    TORN_USDT("TORN","USDT", "0.01", "0.01"),
    
    MATIC_TRY("MATIC","TRY", "0.001", "0.1"),
    
    SOL_GBP("SOL","GBP", "0.01", "0.01"),
    
    BAKE_BTC("BAKE","BTC", "0.00000001", "0.1"),
    
    COTI_BUSD("COTI","BUSD", "0.0001", "1"),
    
    KEEP_BTC("KEEP","BTC", "0.00000001", "1"),
    
    KEEP_BNB("KEEP","BNB", "0.0000001", "1"),
    
    KEEP_BUSD("KEEP","BUSD", "0.0001", "1"),
    
    KEEP_USDT("KEEP","USDT", "0.0001", "1"),
    
    SOL_TRY("SOL","TRY", "0.1", "0.01"),
    
    RUNE_GBP("RUNE","GBP", "0.001", "0.1"),
    
    SOL_BRL("SOL","BRL", "0.1", "0.01"),
    
    SC_BUSD("SC","BUSD", "0.00001", "1"),
    
    CHR_BUSD("CHR","BUSD", "0.0001", "1"),
    
    STMX_BUSD("STMX","BUSD", "0.00001", "1"),
    
    HNT_BUSD("HNT","BUSD", "0.01", "0.01"),
    
    FTT_BUSD("FTT","BUSD", "0.01", "0.01"),
    
    DOCK_BUSD("DOCK","BUSD", "0.00001", "1"),
    
    ADA_BIDR("ADA","BIDR", "1", "0.1"),
    
    ERN_BNB("ERN","BNB", "0.00001", "0.1"),
    
    ERN_BUSD("ERN","BUSD", "0.001", "0.1"),
    
    ERN_USDT("ERN","USDT", "0.001", "0.1"),
    
    KLAY_BTC("KLAY","BTC", "0.00000001", "0.1"),
    
    KLAY_BNB("KLAY","BNB", "0.000001", "1"),
    
    KLAY_BUSD("KLAY","BUSD", "0.001", "0.1"),
    
    KLAY_USDT("KLAY","USDT", "0.001", "0.1"),
    
    RUNE_EUR("RUNE","EUR", "0.001", "0.1"),
    
    MATIC_AUD("MATIC","AUD", "0.001", "0.1"),
    
    DOT_RUB("DOT","RUB", "1", "0.01"),
    
    UTK_BUSD("UTK","BUSD", "0.0001", "1"),
    
    IOTX_BUSD("IOTX","BUSD", "0.00001", "1"),
    
    PHA_USDT("PHA","USDT", "0.0001", "1"),
    
    SOL_RUB("SOL","RUB", "1", "0.01"),
    
    BUSD_UAH("BUSD","UAH", "0.01", "1"),
    
    BOND_BTC("BOND","BTC", "0.0000001", "0.01"),
    
    BOND_BUSD("BOND","BUSD", "0.01", "0.01"),
    
    BOND_USDT("BOND","USDT", "0.01", "0.01"),
    
    MLN_BTC("MLN","BTC", "0.000001", "0.001"),
    
    MLN_BNB("MLN","BNB", "0.0001", "0.001"),
    
    MLN_BUSD("MLN","BUSD", "0.1", "0.001"),
    
    MLN_USDT("MLN","USDT", "0.1", "0.001"),
    
    GRT_TRY("GRT","TRY", "0.001", "1"),
    
    DOT_AUD("DOT","AUD", "0.01", "0.01"),
    
    DEXE_USDT("DEXE","USDT", "0.01", "0.01"),
    
    LTO_BUSD("LTO","BUSD", "0.0001", "1"),
    
    ADX_BUSD("ADX","BUSD", "0.0001", "1"),
    
    QUICK_BTC("QUICK","BTC", "0.000001", "0.001"),
    
    QUICK_BNB("QUICK","BNB", "0.001", "0.001"),
    
    QUICK_BUSD("QUICK","BUSD", "0.1", "0.001"),
    
    C98_USDT("C98","USDT", "0.001", "0.1"),
    
    C98_BUSD("C98","BUSD", "0.001", "0.1"),
    
    C98_BNB("C98","BNB", "0.000001", "0.1"),
    
    C98_BTC("C98","BTC", "0.00000001", "0.1"),
    
    CLV_BTC("CLV","BTC", "0.00000001", "0.1"),
    
    CLV_BNB("CLV","BNB", "0.000001", "0.1"),
    
    CLV_BUSD("CLV","BUSD", "0.001", "0.1"),
    
    CLV_USDT("CLV","USDT", "0.001", "0.1"),
    
    QNT_BTC("QNT","BTC", "0.000001", "0.001"),
    
    QNT_BNB("QNT","BNB", "0.0001", "0.001"),
    
    QNT_BUSD("QNT","BUSD", "0.1", "0.001"),
    
    QNT_USDT("QNT","USDT", "0.1", "0.001"),
    
    FLOW_BTC("FLOW","BTC", "0.0000001", "0.01"),
    
    FLOW_BNB("FLOW","BNB", "0.00001", "0.01"),
    
    FLOW_BUSD("FLOW","BUSD", "0.01", "0.01"),
    
    FLOW_USDT("FLOW","USDT", "0.01", "0.01"),
    
    XEC_BUSD("XEC","BUSD", "0.00000001", "1"),
    
    AXS_BRL("AXS","BRL", "0.1", "0.01"),
    
    AXS_AUD("AXS","AUD", "0.01", "0.01"),
    
    TVK_USDT("TVK","USDT", "0.0001", "1"),
    
    MINA_BTC("MINA","BTC", "0.00000001", "0.1"),
    
    MINA_BNB("MINA","BNB", "0.000001", "0.1"),
    
    MINA_BUSD("MINA","BUSD", "0.001", "0.1"),
    
    MINA_USDT("MINA","USDT", "0.001", "0.1"),
    
    RAY_BNB("RAY","BNB", "0.00001", "0.1"),
    
    RAY_BUSD("RAY","BUSD", "0.001", "0.1"),
    
    RAY_USDT("RAY","USDT", "0.001", "0.1"),
    
    FARM_BTC("FARM","BTC", "0.000001", "0.001"),
    
    FARM_BNB("FARM","BNB", "0.0001", "0.001"),
    
    FARM_BUSD("FARM","BUSD", "0.1", "0.001"),
    
    FARM_USDT("FARM","USDT", "0.1", "0.001"),
    
    ALPACA_BTC("ALPACA","BTC", "0.00000001", "0.1"),
    
    ALPACA_BNB("ALPACA","BNB", "0.000001", "0.1"),
    
    ALPACA_BUSD("ALPACA","BUSD", "0.0001", "0.1"),
    
    ALPACA_USDT("ALPACA","USDT", "0.0001", "0.1"),
    
    TLM_TRY("TLM","TRY", "0.001", "1"),
    
    QUICK_USDT("QUICK","USDT", "0.1", "0.001"),
    
    ORN_BUSD("ORN","BUSD", "0.001", "0.1"),
    
    MBOX_BTC("MBOX","BTC", "0.00000001", "0.1"),
    
    MBOX_BNB("MBOX","BNB", "0.000001", "0.1"),
    
    MBOX_BUSD("MBOX","BUSD", "0.001", "0.1"),
    
    MBOX_USDT("MBOX","USDT", "0.001", "0.1"),
    
    VGX_BTC("VGX","BTC", "0.00000001", "0.1"),
    
    VGX_ETH("VGX","ETH", "0.000001", "0.1"),
    
    FOR_USDT("FOR","USDT", "0.00001", "1"),
    
    REQ_USDT("REQ","USDT", "0.0001", "1"),
    
    GHST_USDT("GHST","USDT", "0.001", "0.1"),
    
    TRU_RUB("TRU","RUB", "0.01", "1"),
    
    FIS_BRL("FIS","BRL", "0.01", "0.1"),
    
    WAXP_USDT("WAXP","USDT", "0.0001", "1"),
    
    WAXP_BUSD("WAXP","BUSD", "0.0001", "1"),
    
    WAXP_BNB("WAXP","BNB", "0.0000001", "1"),
    
    WAXP_BTC("WAXP","BTC", "0.00000001", "1"),
    
    TRIBE_BTC("TRIBE","BTC", "0.00000001", "1"),
    
    TRIBE_BNB("TRIBE","BNB", "0.000001", "1"),
    
    TRIBE_BUSD("TRIBE","BUSD", "0.0001", "1"),
    
    TRIBE_USDT("TRIBE","USDT", "0.0001", "1"),
    
    GNO_USDT("GNO","USDT", "0.1", "0.001"),
    
    GNO_BUSD("GNO","BUSD", "0.1", "0.001"),
    
    GNO_BNB("GNO","BNB", "0.0001", "0.001"),
    
    GNO_BTC("GNO","BTC", "0.000001", "0.001"),
    
    ARPA_TRY("ARPA","TRY", "0.0001", "1"),
    
    PROM_BTC("PROM","BTC", "0.0000001", "0.01"),
    
    MTL_BUSD("MTL","BUSD", "0.001", "0.1"),
    
    OGN_BUSD("OGN","BUSD", "0.0001", "0.1"),
    
    XEC_USDT("XEC","USDT", "0.00000001", "1"),
    
    C98_BRL("C98","BRL", "0.01", "0.1"),
    
    SOL_AUD("SOL","AUD", "0.01", "0.001"),
    
    SUSHI_BIDR("SUSHI","BIDR", "1", "0.01"),
    
    XRP_BIDR("XRP","BIDR", "1", "0.1"),
    
    POLY_BUSD("POLY","BUSD", "0.0001", "0.1"),
    
    ELF_USDT("ELF","USDT", "0.0001", "0.1"),
    
    DYDX_USDT("DYDX","USDT", "0.001", "0.01"),
    
    DYDX_BUSD("DYDX","BUSD", "0.001", "0.01"),
    
    DYDX_BNB("DYDX","BNB", "0.000001", "0.01"),
    
    DYDX_BTC("DYDX","BTC", "0.00000001", "0.01"),
    
    ELF_BUSD("ELF","BUSD", "0.0001", "0.1"),
    
    POLY_USDT("POLY","USDT", "0.0001", "0.1"),
    
    IDEX_USDT("IDEX","USDT", "0.00001", "0.1"),
    
    VIDT_USDT("VIDT","USDT", "0.0001", "0.1"),
    
    SOL_BIDR("SOL","BIDR", "1", "0.0001"),
    
    AXS_BIDR("AXS","BIDR", "1", "0.001"),
    
    BTC_USDP("BTC","USDP", "0.01", "0.00001"),
    
    ETH_USDP("ETH","USDP", "0.01", "0.0001"),
    
    BNB_USDP("BNB","USDP", "0.01", "0.001"),
    
    USDP_BUSD("USDP","BUSD", "0.0001", "0.01"),
    
    USDP_USDT("USDP","USDT", "0.0001", "0.01"),
    
    GALA_USDT("GALA","USDT", "0.00001", "1"),
    
    GALA_BUSD("GALA","BUSD", "0.00001", "1"),
    
    GALA_BNB("GALA","BNB", "0.00000001", "1"),
    
    GALA_BTC("GALA","BTC", "0.00000001", "1"),
    
    FTM_BIDR("FTM","BIDR", "1", "0.1"),
    
    KSM_AUD("KSM","AUD", "0.1", "0.001"),
    
    SUN_BUSD("SUN","BUSD", "0.00001", "1"),
    
    ILV_USDT("ILV","USDT", "0.1", "0.001"),
    
    ILV_BUSD("ILV","BUSD", "0.1", "0.001"),
    
    ILV_BNB("ILV","BNB", "0.001", "0.001"),
    
    ILV_BTC("ILV","BTC", "0.00001", "0.001"),
    
    REN_BUSD("REN","BUSD", "0.0001", "1"),
    
    YGG_USDT("YGG","USDT", "0.001", "0.1"),
    
    YGG_BUSD("YGG","BUSD", "0.001", "0.1"),
    
    YGG_BNB("YGG","BNB", "0.00001", "0.1"),
    
    YGG_BTC("YGG","BTC", "0.0000001", "0.1"),
    
    STX_BUSD("STX","BUSD", "0.001", "0.1"),
    
    SYS_USDT("SYS","USDT", "0.0001", "1"),
    
    DF_USDT("DF","USDT", "0.0001", "1"),
    
    SOL_USDC("SOL","USDC", "0.01", "0.01"),
    
    ARPA_RUB("ARPA","RUB", "0.001", "1"),
    
    LTC_UAH("LTC","UAH", "1", "0.001"),
    
    FET_BUSD("FET","BUSD", "0.0001", "1"),
    
    ARPA_BUSD("ARPA","BUSD", "0.00001", "0.1"),
    
    LSK_BUSD("LSK","BUSD", "0.001", "0.1"),
    
    AVAX_BIDR("AVAX","BIDR", "1", "0.01"),
    
    ALICE_BIDR("ALICE","BIDR", "1", "0.01"),
    
    FIDA_USDT("FIDA","USDT", "0.001", "0.1"),
    
    FIDA_BUSD("FIDA","BUSD", "0.001", "0.1"),
    
    FIDA_BNB("FIDA","BNB", "0.00001", "0.1"),
    
    FIDA_BTC("FIDA","BTC", "0.0000001", "0.1"),
    
    DENT_BUSD("DENT","BUSD", "0.000001", "1"),
    
    FRONT_USDT("FRONT","USDT", "0.0001", "1"),
    
    CVP_USDT("CVP","USDT", "0.001", "0.1"),
    
    AGLD_BTC("AGLD","BTC", "0.00000001", "0.1"),
    
    AGLD_BNB("AGLD","BNB", "0.000001", "0.1"),
    
    AGLD_BUSD("AGLD","BUSD", "0.001", "0.1"),
    
    AGLD_USDT("AGLD","USDT", "0.001", "0.1"),
    
    RAD_BTC("RAD","BTC", "0.0000001", "0.1"),
    
    RAD_BNB("RAD","BNB", "0.00001", "0.1"),
    
    RAD_BUSD("RAD","BUSD", "0.001", "0.1"),
    
    RAD_USDT("RAD","USDT", "0.001", "0.1"),
    
    HIVE_BUSD("HIVE","BUSD", "0.0001", "1"),
    
    STPT_BUSD("STPT","BUSD", "0.00001", "0.1"),
    
    BETA_BTC("BETA","BTC", "0.00000001", "1"),
    
    BETA_BNB("BETA","BNB", "0.0000001", "1"),
    
    BETA_BUSD("BETA","BUSD", "0.00001", "1"),
    
    BETA_USDT("BETA","USDT", "0.00001", "1"),
    
    SHIB_AUD("SHIB","AUD", "0.00000001", "1"),
    
    RARE_BTC("RARE","BTC", "0.00000001", "0.1"),
    
    RARE_BNB("RARE","BNB", "0.000001", "0.1"),
    
    RARE_BUSD("RARE","BUSD", "0.001", "0.1"),
    
    RARE_USDT("RARE","USDT", "0.001", "0.1"),
    
    AVAX_BRL("AVAX","BRL", "0.1", "0.01"),
    
    AVAX_AUD("AVAX","AUD", "0.01", "0.01"),
    
    LUNA_AUD("LUNA","AUD", "0.01", "0.01"),
    
    TROY_BUSD("TROY","BUSD", "0.000001", "1"),
    
    AXS_ETH("AXS","ETH", "0.00001", "0.001"),
    
    FTM_ETH("FTM","ETH", "0.0000001", "0.1"),
    
    SOL_ETH("SOL","ETH", "0.00001", "0.001"),
    
    SSV_BTC("SSV","BTC", "0.0000001", "0.01"),
    
    SSV_ETH("SSV","ETH", "0.000001", "0.01"),
    
    LAZIO_TRY("LAZIO","TRY", "0.001", "0.01"),
    
    LAZIO_EUR("LAZIO","EUR", "0.0001", "0.01"),
    
    LAZIO_BTC("LAZIO","BTC", "0.00000001", "0.01"),
    
    LAZIO_USDT("LAZIO","USDT", "0.0001", "0.01"),
    
    CHESS_BTC("CHESS","BTC", "0.00000001", "0.1"),
    
    CHESS_BNB("CHESS","BNB", "0.000001", "0.1"),
    
    CHESS_BUSD("CHESS","BUSD", "0.001", "0.1"),
    
    CHESS_USDT("CHESS","USDT", "0.001", "0.1"),
    
    FTM_AUD("FTM","AUD", "0.0001", "1"),
    
    FTM_BRL("FTM","BRL", "0.01", "0.1"),
    
    SCRT_BUSD("SCRT","BUSD", "0.001", "0.1"),
    
    ADX_USDT("ADX","USDT", "0.0001", "1"),
    
    AUCTION_USDT("AUCTION","USDT", "0.01", "0.01"),
    
    CELO_BUSD("CELO","BUSD", "0.001", "0.1"),
    
    FTM_RUB("FTM","RUB", "0.1", "0.1"),
    
    NU_AUD("NU","AUD", "0.0001", "1"),
    
    NU_RUB("NU","RUB", "0.01", "0.1"),
    
    REEF_TRY("REEF","TRY", "0.0001", "1"),
    
    REEF_BIDR("REEF","BIDR", "0.1", "1"),
    
    SHIB_DOGE("SHIB","DOGE", "0.0000001", "1"),
    
    DAR_USDT("DAR","USDT", "0.00001", "1"),
    
    DAR_BUSD("DAR","BUSD", "0.00001", "1"),
    
    DAR_BNB("DAR","BNB", "0.00000001", "1"),
    
    DAR_BTC("DAR","BTC", "0.00000001", "1"),
    
    BNX_BTC("BNX","BTC", "0.000001", "0.001"),
    
    BNX_BNB("BNX","BNB", "0.0001", "0.001"),
    
    BNX_BUSD("BNX","BUSD", "0.1", "0.001"),
    
    BNX_USDT("BNX","USDT", "0.1", "0.001"),
    
    RGT_USDT("RGT","USDT", "0.01", "0.01"),
    
    RGT_BTC("RGT","BTC", "0.0000001", "0.01"),
    
    RGT_BUSD("RGT","BUSD", "0.01", "0.01"),
    
    RGT_BNB("RGT","BNB", "0.00001", "0.01"),
    
    LAZIO_BUSD("LAZIO","BUSD", "0.0001", "0.01"),
    
    OXT_BUSD("OXT","BUSD", "0.0001", "1"),
    
    MANA_TRY("MANA","TRY", "0.01", "0.1"),
    
    ALGO_RUB("ALGO","RUB", "0.1", "0.1"),
    
    SHIB_UAH("SHIB","UAH", "0.000001", "1"),
    
    LUNA_BIDR("LUNA","BIDR", "1", "0.01"),
    
    AUD_USDC("AUD","USDC", "0.0001", "1"),
    
    MOVR_BTC("MOVR","BTC", "0.000001", "0.001"),
    
    MOVR_BNB("MOVR","BNB", "0.0001", "0.001"),
    
    MOVR_BUSD("MOVR","BUSD", "0.1", "0.001"),
    
    MOVR_USDT("MOVR","USDT", "0.1", "0.001"),
    
    CITY_BTC("CITY","BTC", "0.0000001", "0.01"),
    
    CITY_BNB("CITY","BNB", "0.00001", "0.01"),
    
    CITY_BUSD("CITY","BUSD", "0.01", "0.01"),
    
    CITY_USDT("CITY","USDT", "0.01", "0.01"),
    
    ENS_BTC("ENS","BTC", "0.0000001", "0.01"),
    
    ENS_BNB("ENS","BNB", "0.00001", "0.01"),
    
    ENS_BUSD("ENS","BUSD", "0.01", "0.01"),
    
    ENS_USDT("ENS","USDT", "0.01", "0.01"),
    
    SAND_ETH("SAND","ETH", "0.0000001", "0.1"),
    
    DOT_ETH("DOT","ETH", "0.000001", "0.01"),
    
    MATIC_ETH("MATIC","ETH", "0.0000001", "0.1"),
    
    ANKR_BUSD("ANKR","BUSD", "0.00001", "0.1"),
    
    SAND_TRY("SAND","TRY", "0.01", "0.1"),
    
    MANA_BRL("MANA","BRL", "0.01", "0.1"),
    
    KP3R_USDT("KP3R","USDT", "0.01", "0.01"),
    
    QI_USDT("QI","USDT", "0.0001", "1"),
    
    QI_BUSD("QI","BUSD", "0.0001", "1"),
    
    QI_BNB("QI","BNB", "0.0000001", "1"),
    
    QI_BTC("QI","BTC", "0.00000001", "1"),
    
    PORTO_BTC("PORTO","BTC", "0.00000001", "0.01"),
    
    PORTO_USDT("PORTO","USDT", "0.0001", "0.01"),
    
    PORTO_TRY("PORTO","TRY", "0.01", "0.01"),
    
    PORTO_EUR("PORTO","EUR", "0.0001", "0.01"),
    
    POWR_USDT("POWR","USDT", "0.0001", "1"),
    
    POWR_BUSD("POWR","BUSD", "0.0001", "1"),
    
    AVAX_ETH("AVAX","ETH", "0.00001", "0.01"),
    
    SLP_TRY("SLP","TRY", "0.0001", "1"),
    
    FIS_TRY("FIS","TRY", "0.01", "0.1"),
    
    LRC_TRY("LRC","TRY", "0.01", "0.1"),
    
    CHR_ETH("CHR","ETH", "0.0000001", "0.1"),
    
    FIS_BIDR("FIS","BIDR", "1", "0.1"),
    
    VGX_USDT("VGX","USDT", "0.001", "0.1"),
    
    GALA_ETH("GALA","ETH", "0.00000001", "1"),
    
    JASMY_USDT("JASMY","USDT", "0.0001", "0.1"),
    
    JASMY_BUSD("JASMY","BUSD", "0.0001", "0.1"),
    
    JASMY_BNB("JASMY","BNB", "0.0000001", "0.1"),
    
    JASMY_BTC("JASMY","BTC", "0.00000001", "0.1"),
    
    AMP_BTC("AMP","BTC", "0.00000001", "1"),
    
    AMP_BNB("AMP","BNB", "0.00000001", "1"),
    
    AMP_BUSD("AMP","BUSD", "0.00001", "1"),
    
    AMP_USDT("AMP","USDT", "0.00001", "1"),
    
    PLA_BTC("PLA","BTC", "0.00000001", "0.01"),
    
    PLA_BNB("PLA","BNB", "0.000001", "0.01"),
    
    PLA_BUSD("PLA","BUSD", "0.001", "0.01"),
    
    PLA_USDT("PLA","USDT", "0.001", "0.01"),
    
    PYR_BTC("PYR","BTC", "0.0000001", "0.001"),
    
    PYR_BUSD("PYR","BUSD", "0.01", "0.001"),
    
    PYR_USDT("PYR","USDT", "0.01", "0.001"),
    
    RNDR_BTC("RNDR","BTC", "0.0000001", "0.01"),
    
    RNDR_USDT("RNDR","USDT", "0.001", "0.01"),
    
    RNDR_BUSD("RNDR","BUSD", "0.001", "0.01"),
    
    ALCX_BTC("ALCX","BTC", "0.000001", "0.0001"),
    
    ALCX_BUSD("ALCX","BUSD", "0.1", "0.0001"),
    
    ALCX_USDT("ALCX","USDT", "0.1", "0.0001"),
    
    SANTOS_BTC("SANTOS","BTC", "0.00000001", "0.01"),
    
    SANTOS_USDT("SANTOS","USDT", "0.001", "0.01"),
    
    SANTOS_BRL("SANTOS","BRL", "0.01", "0.01"),
    
    SANTOS_TRY("SANTOS","TRY", "0.01", "0.01"),
    
    MC_BTC("MC","BTC", "0.0000001", "0.01"),
    
    MC_BUSD("MC","BUSD", "0.01", "0.01"),
    
    MC_USDT("MC","USDT", "0.01", "0.01"),
    
    BEL_TRY("BEL","TRY", "0.01", "0.01"),
    
    COCOS_BUSD("COCOS","BUSD", "0.0001", "1"),
    
    DENT_TRY("DENT","TRY", "0.00001", "1"),
    
    ENJ_TRY("ENJ","TRY", "0.01", "0.01"),
    
    NEO_RUB("NEO","RUB", "1", "0.001"),
    
    SAND_AUD("SAND","AUD", "0.0001", "1"),
    
    SLP_BIDR("SLP","BIDR", "0.1", "1"),
    
    ANY_BTC("ANY","BTC", "0.0000001", "0.01"),
    
    ANY_BUSD("ANY","BUSD", "0.01", "0.01"),
    
    ANY_USDT("ANY","USDT", "0.01", "0.01"),
    
    BICO_BTC("BICO","BTC", "0.0000001", "0.01"),
    
    BICO_BUSD("BICO","BUSD", "0.001", "0.01"),
    
    BICO_USDT("BICO","USDT", "0.001", "0.01"),
    
    FLUX_BTC("FLUX","BTC", "0.00000001", "0.01"),
    
    FLUX_BUSD("FLUX","BUSD", "0.001", "0.01"),
    
    FLUX_USDT("FLUX","USDT", "0.001", "0.01"),
    
    ALICE_TRY("ALICE","TRY", "0.1", "0.001"),
    
    FXS_USDT("FXS","USDT", "0.001", "0.1"),
    
    GALA_BRL("GALA","BRL", "0.001", "0.1"),
    
    GALA_TRY("GALA","TRY", "0.001", "0.1"),
    
    LUNA_TRY("LUNA","TRY", "0.1", "0.001"),
    
    REQ_BUSD("REQ","BUSD", "0.0001", "1"),
    
    SAND_BRL("SAND","BRL", "0.01", "0.01"),
    
    MANA_BIDR("MANA","BIDR", "1", "0.01"),
    
    SAND_BIDR("SAND","BIDR", "1", "0.01"),
    
    VOXEL_BTC("VOXEL","BTC", "0.00000001", "0.1"),
    
    VOXEL_BNB("VOXEL","BNB", "0.0000001", "0.1"),
    
    VOXEL_BUSD("VOXEL","BUSD", "0.0001", "0.1"),
    
    VOXEL_USDT("VOXEL","USDT", "0.0001", "0.1"),
    
    COS_BUSD("COS","BUSD", "0.00001", "0.1"),
    
    CTXC_BUSD("CTXC","BUSD", "0.0001", "1"),
    
    FTM_TRY("FTM","TRY", "0.01", "0.01"),
    
    MANA_BNB("MANA","BNB", "0.000001", "0.01"),
    
    MINA_TRY("MINA","TRY", "0.01", "0.01"),
    
    XTZ_TRY("XTZ","TRY", "0.01", "0.01"),
    
    HIGH_BTC("HIGH","BTC", "0.0000001", "0.001"),
    
    HIGH_BUSD("HIGH","BUSD", "0.01", "0.001"),
    
    HIGH_USDT("HIGH","USDT", "0.01", "0.001"),
    
    CVX_BTC("CVX","BTC", "0.0000001", "0.001"),
    
    CVX_BUSD("CVX","BUSD", "0.01", "0.001"),
    
    CVX_USDT("CVX","USDT", "0.01", "0.001"),
    
    PEOPLE_BTC("PEOPLE","BTC", "0.00000001", "0.1"),
    
    PEOPLE_BUSD("PEOPLE","BUSD", "0.0001", "0.1"),
    
    PEOPLE_USDT("PEOPLE","USDT", "0.0001", "0.1"),
    
    OOKI_BUSD("OOKI","BUSD", "0.00001", "1"),
    
    OOKI_USDT("OOKI","USDT", "0.00001", "1"),
    
    COCOS_TRY("COCOS","TRY", "0.01", "0.01"),
    
    GXS_BNB("GXS","BNB", "0.000001", "0.01"),
    
    LINK_BNB("LINK","BNB", "0.00001", "0.001"),
    
    LUNA_ETH("LUNA","ETH", "0.00001", "0.001"),
    
    MDT_BUSD("MDT","BUSD", "0.00001", "0.1"),
    
    NULS_BUSD("NULS","BUSD", "0.0001", "1"),
    
    SPELL_BTC("SPELL","BTC", "0.00000001", "1"),
    
    SPELL_USDT("SPELL","USDT", "0.00001", "1"),
    
    SPELL_BUSD("SPELL","BUSD", "0.00001", "1"),
    
    UST_BTC("UST","BTC", "0.00000001", "0.01"),
    
    UST_BUSD("UST","BUSD", "0.0001", "1"),
    
    UST_USDT("UST","USDT", "0.0001", "1"),
    
    JOE_BTC("JOE","BTC", "0.00000001", "0.01"),
    
    JOE_BUSD("JOE","BUSD", "0.001", "0.01"),
    
    JOE_USDT("JOE","USDT", "0.001", "0.01"),
    
    ATOM_ETH("ATOM","ETH", "0.00001", "0.001"),
    
    DUSK_BUSD("DUSK","BUSD", "0.0001", "1"),
    
    EGLD_ETH("EGLD","ETH", "0.00001", "0.0001"),
    
    ICP_ETH("ICP","ETH", "0.000001", "0.001"),
    
    LUNA_BRL("LUNA","BRL", "0.1", "0.001"),
    
    LUNA_UST("LUNA","UST", "0.01", "0.01"),
    
    NEAR_ETH("NEAR","ETH", "0.000001", "0.001"),
    
    ROSE_BNB("ROSE","BNB", "0.0000001", "0.1"),
    
    VOXEL_ETH("VOXEL","ETH", "0.0000001", "0.01"),
    
    ALICE_BNB("ALICE","BNB", "0.00001", "0.001"),
    
    ATOM_TRY("ATOM","TRY", "0.1", "0.001"),
    
    ETH_UST("ETH","UST", "0.01", "0.0001"),
    
    GALA_AUD("GALA","AUD", "0.00001", "1"),
    
    LRC_BNB("LRC","BNB", "0.000001", "0.01"),
    
    ONE_ETH("ONE","ETH", "0.00000001", "0.1"),
    
    OOKI_BNB("OOKI","BNB", "0.00000001", "1"),
    
    ;
    // generate code from binance api
    // generate code from binance api
    // generate code from binance api

    public final String name;
    public final String baseAsset;
    public final String quoteAsset;
    public final BigDecimal pricePrecision;
    public final BigDecimal quantityPrecision;

    Symbol(String baseAsset, String quoteAsset, String precision, String quantity) {
        this.baseAsset = baseAsset;
        this.quoteAsset = quoteAsset;
        this.name = baseAsset + quoteAsset;
        this.pricePrecision = new BigDecimal(precision);
        this.quantityPrecision = new BigDecimal(quantity);
    }

    public String lowerCaseName() {
        return name.toLowerCase();
    }

    public String upperCaseName() {
        return name.toUpperCase();
    }

    @Override
    public String getStr() {
        return name;
    }

    @Override
    public Symbol[] getValues() {
        return values();
    }

}
