package com.sidemesh.binance.bot;

import com.sidemesh.binance.bot.util.StringEnum;

import java.math.BigDecimal;

public enum Symbol implements StringEnum<Symbol> {

    // generate code from binance api
    // generate code from binance api
    // generate code from binance api
    
    ETH_BTC("ETHBTC", "0.000001", "0.0001"),
    
    LTC_BTC("LTCBTC", "0.000001", "0.001"),
    
    BNB_BTC("BNBBTC", "0.000001", "0.001"),
    
    NEO_BTC("NEOBTC", "0.000001", "0.01"),
    
    QTUM_ETH("QTUMETH", "0.000001", "0.1"),
    
    EOS_ETH("EOSETH", "0.000001", "0.1"),
    
    SNT_ETH("SNTETH", "0.00000001", "1"),
    
    BNT_ETH("BNTETH", "0.000001", "0.1"),
    
    GAS_BTC("GASBTC", "0.0000001", "0.1"),
    
    BNB_ETH("BNBETH", "0.0001", "0.001"),
    
    BTC_USDT("BTCUSDT", "0.01", "0.00001"),
    
    ETH_USDT("ETHUSDT", "0.01", "0.0001"),
    
    WTC_BTC("WTCBTC", "0.00000001", "1"),
    
    LRC_BTC("LRCBTC", "0.00000001", "1"),
    
    LRC_ETH("LRCETH", "0.00000001", "1"),
    
    QTUM_BTC("QTUMBTC", "0.0000001", "0.1"),
    
    YOYO_BTC("YOYOBTC", "0.00000001", "1"),
    
    OMG_BTC("OMGBTC", "0.0000001", "0.1"),
    
    OMG_ETH("OMGETH", "0.000001", "0.1"),
    
    ZRX_BTC("ZRXBTC", "0.00000001", "1"),
    
    ZRX_ETH("ZRXETH", "0.0000001", "1"),
    
    KNC_BTC("KNCBTC", "0.00000001", "0.1"),
    
    KNC_ETH("KNCETH", "0.0000001", "0.1"),
    
    FUN_ETH("FUNETH", "0.00000001", "1"),
    
    SNM_BTC("SNMBTC", "0.00000001", "1"),
    
    NEO_ETH("NEOETH", "0.00001", "0.01"),
    
    IOTA_BTC("IOTABTC", "0.00000001", "1"),
    
    IOTA_ETH("IOTAETH", "0.0000001", "1"),
    
    LINK_BTC("LINKBTC", "0.0000001", "0.01"),
    
    LINK_ETH("LINKETH", "0.000001", "0.01"),
    
    XVG_BTC("XVGBTC", "0.00000001", "1"),
    
    XVG_ETH("XVGETH", "0.00000001", "1"),
    
    MDA_BTC("MDABTC", "0.00000001", "1"),
    
    MTL_BTC("MTLBTC", "0.00000001", "0.1"),
    
    MTL_ETH("MTLETH", "0.0000001", "0.1"),
    
    EOS_BTC("EOSBTC", "0.0000001", "0.1"),
    
    SNT_BTC("SNTBTC", "0.00000001", "1"),
    
    ETC_ETH("ETCETH", "0.00001", "0.01"),
    
    ETC_BTC("ETCBTC", "0.000001", "0.01"),
    
    MTH_BTC("MTHBTC", "0.00000001", "1"),
    
    DNT_BTC("DNTBTC", "0.00000001", "1"),
    
    ZEC_BTC("ZECBTC", "0.000001", "0.001"),
    
    ZEC_ETH("ZECETH", "0.00001", "0.001"),
    
    BNT_BTC("BNTBTC", "0.00000001", "0.1"),
    
    AST_BTC("ASTBTC", "0.00000001", "1"),
    
    DASH_BTC("DASHBTC", "0.000001", "0.001"),
    
    DASH_ETH("DASHETH", "0.00001", "0.001"),
    
    OAX_BTC("OAXBTC", "0.00000001", "1"),
    
    BTG_BTC("BTGBTC", "0.000001", "0.01"),
    
    REQ_BTC("REQBTC", "0.00000001", "1"),
    
    VIB_BTC("VIBBTC", "0.00000001", "1"),
    
    VIB_ETH("VIBETH", "0.00000001", "1"),
    
    TRX_BTC("TRXBTC", "0.00000001", "1"),
    
    TRX_ETH("TRXETH", "0.00000001", "1"),
    
    POWR_BTC("POWRBTC", "0.00000001", "1"),
    
    POWR_ETH("POWRETH", "0.0000001", "1"),
    
    ARK_BTC("ARKBTC", "0.00000001", "0.1"),
    
    XRP_BTC("XRPBTC", "0.00000001", "1"),
    
    XRP_ETH("XRPETH", "0.0000001", "1"),
    
    ENJ_BTC("ENJBTC", "0.00000001", "0.1"),
    
    ENJ_ETH("ENJETH", "0.0000001", "0.1"),
    
    STORJ_BTC("STORJBTC", "0.00000001", "1"),
    
    BNB_USDT("BNBUSDT", "0.1", "0.001"),
    
    KMD_BTC("KMDBTC", "0.00000001", "1"),
    
    KMD_ETH("KMDETH", "0.0000001", "1"),
    
    NULS_BTC("NULSBTC", "0.00000001", "1"),
    
    XMR_BTC("XMRBTC", "0.000001", "0.001"),
    
    XMR_ETH("XMRETH", "0.00001", "0.001"),
    
    AMB_BTC("AMBBTC", "0.00000001", "1"),
    
    BAT_BTC("BATBTC", "0.00000001", "1"),
    
    BAT_ETH("BATETH", "0.0000001", "1"),
    
    GXS_BTC("GXSBTC", "0.00000001", "1"),
    
    GXS_ETH("GXSETH", "0.0000001", "1"),
    
    NEO_USDT("NEOUSDT", "0.01", "0.01"),
    
    NEO_BNB("NEOBNB", "0.0001", "0.01"),
    
    QSP_BTC("QSPBTC", "0.00000001", "1"),
    
    QSP_ETH("QSPETH", "0.00000001", "1"),
    
    BTS_BTC("BTSBTC", "0.00000001", "1"),
    
    LSK_BTC("LSKBTC", "0.00000001", "0.1"),
    
    LSK_ETH("LSKETH", "0.000001", "0.1"),
    
    MANA_BTC("MANABTC", "0.00000001", "1"),
    
    MANA_ETH("MANAETH", "0.0000001", "1"),
    
    BCD_BTC("BCDBTC", "0.00000001", "0.1"),
    
    IOTA_BNB("IOTABNB", "0.000001", "1"),
    
    ADX_BTC("ADXBTC", "0.00000001", "1"),
    
    ADX_ETH("ADXETH", "0.0000001", "1"),
    
    ADA_BTC("ADABTC", "0.00000001", "0.1"),
    
    ADA_ETH("ADAETH", "0.0000001", "0.1"),
    
    XLM_BTC("XLMBTC", "0.00000001", "1"),
    
    XLM_ETH("XLMETH", "0.0000001", "1"),
    
    XLM_BNB("XLMBNB", "0.0000001", "1"),
    
    CND_BTC("CNDBTC", "0.00000001", "1"),
    
    WABI_BTC("WABIBTC", "0.00000001", "1"),
    
    WABI_BNB("WABIBNB", "0.0000001", "1"),
    
    LTC_ETH("LTCETH", "0.00001", "0.001"),
    
    LTC_USDT("LTCUSDT", "0.1", "0.001"),
    
    LTC_BNB("LTCBNB", "0.0001", "0.001"),
    
    WAVES_BTC("WAVESBTC", "0.0000001", "0.01"),
    
    WAVES_ETH("WAVESETH", "0.000001", "0.01"),
    
    WAVES_BNB("WAVESBNB", "0.00001", "0.01"),
    
    GTO_BTC("GTOBTC", "0.00000001", "1"),
    
    ICX_BTC("ICXBTC", "0.00000001", "1"),
    
    ICX_ETH("ICXETH", "0.0000001", "1"),
    
    ELF_BTC("ELFBTC", "0.00000001", "1"),
    
    ELF_ETH("ELFETH", "0.0000001", "1"),
    
    AION_BTC("AIONBTC", "0.00000001", "1"),
    
    AION_ETH("AIONETH", "0.00000001", "1"),
    
    NEBL_BTC("NEBLBTC", "0.00000001", "0.1"),
    
    NEBL_ETH("NEBLETH", "0.0000001", "1"),
    
    BRD_BTC("BRDBTC", "0.00000001", "1"),
    
    BRD_ETH("BRDETH", "0.00000001", "1"),
    
    NAV_BTC("NAVBTC", "0.00000001", "1"),
    
    RLC_BTC("RLCBTC", "0.00000001", "0.1"),
    
    RLC_ETH("RLCETH", "0.000001", "0.1"),
    
    PIVX_BTC("PIVXBTC", "0.00000001", "1"),
    
    PIVX_ETH("PIVXETH", "0.0000001", "1"),
    
    IOST_BTC("IOSTBTC", "0.00000001", "1"),
    
    IOST_ETH("IOSTETH", "0.00000001", "1"),
    
    STEEM_BTC("STEEMBTC", "0.00000001", "1"),
    
    STEEM_ETH("STEEMETH", "0.0000001", "1"),
    
    NANO_BTC("NANOBTC", "0.0000001", "0.1"),
    
    NANO_ETH("NANOETH", "0.000001", "0.1"),
    
    BLZ_BTC("BLZBTC", "0.00000001", "1"),
    
    BLZ_ETH("BLZETH", "0.00000001", "1"),
    
    NCASH_ETH("NCASHETH", "0.00000001", "1"),
    
    ZIL_BTC("ZILBTC", "0.00000001", "1"),
    
    ZIL_ETH("ZILETH", "0.00000001", "1"),
    
    ZIL_BNB("ZILBNB", "0.0000001", "1"),
    
    ONT_BTC("ONTBTC", "0.00000001", "1"),
    
    ONT_ETH("ONTETH", "0.0000001", "1"),
    
    QTUM_USDT("QTUMUSDT", "0.001", "0.1"),
    
    XEM_BTC("XEMBTC", "0.00000001", "1"),
    
    XEM_ETH("XEMETH", "0.00000001", "1"),
    
    WAN_BTC("WANBTC", "0.00000001", "1"),
    
    WAN_ETH("WANETH", "0.0000001", "1"),
    
    QLC_BTC("QLCBTC", "0.00000001", "1"),
    
    QLC_ETH("QLCETH", "0.00000001", "1"),
    
    SYS_BTC("SYSBTC", "0.00000001", "1"),
    
    GRS_BTC("GRSBTC", "0.00000001", "1"),
    
    ADA_USDT("ADAUSDT", "0.001", "0.1"),
    
    ADA_BNB("ADABNB", "0.000001", "0.1"),
    
    LOOM_BTC("LOOMBTC", "0.00000001", "1"),
    
    LOOM_ETH("LOOMETH", "0.00000001", "1"),
    
    XRP_USDT("XRPUSDT", "0.0001", "1"),
    
    REP_BTC("REPBTC", "0.0000001", "0.01"),
    
    REP_ETH("REPETH", "0.000001", "0.01"),
    
    BTC_TUSD("BTCTUSD", "0.01", "0.00001"),
    
    ETH_TUSD("ETHTUSD", "0.01", "0.0001"),
    
    ZEN_BTC("ZENBTC", "0.000001", "0.01"),
    
    ZEN_ETH("ZENETH", "0.00001", "0.01"),
    
    ZEN_BNB("ZENBNB", "0.0001", "0.01"),
    
    EOS_USDT("EOSUSDT", "0.001", "0.1"),
    
    EOS_BNB("EOSBNB", "0.00001", "0.1"),
    
    CVC_BTC("CVCBTC", "0.00000001", "1"),
    
    CVC_ETH("CVCETH", "0.0000001", "1"),
    
    THETA_BTC("THETABTC", "0.0000001", "0.1"),
    
    THETA_ETH("THETAETH", "0.000001", "0.1"),
    
    THETA_BNB("THETABNB", "0.00001", "0.1"),
    
    XRP_BNB("XRPBNB", "0.000001", "1"),
    
    TUSD_USDT("TUSDUSDT", "0.0001", "1"),
    
    IOTA_USDT("IOTAUSDT", "0.0001", "1"),
    
    XLM_USDT("XLMUSDT", "0.0001", "1"),
    
    IOTX_BTC("IOTXBTC", "0.00000001", "1"),
    
    IOTX_ETH("IOTXETH", "0.00000001", "1"),
    
    QKC_BTC("QKCBTC", "0.00000001", "1"),
    
    QKC_ETH("QKCETH", "0.00000001", "1"),
    
    NXS_BTC("NXSBTC", "0.00000001", "1"),
    
    ENJ_BNB("ENJBNB", "0.000001", "0.1"),
    
    DATA_BTC("DATABTC", "0.00000001", "1"),
    
    DATA_ETH("DATAETH", "0.00000001", "1"),
    
    ONT_USDT("ONTUSDT", "0.0001", "1"),
    
    TRX_BNB("TRXBNB", "0.0000001", "1"),
    
    TRX_USDT("TRXUSDT", "0.00001", "0.1"),
    
    ETC_USDT("ETCUSDT", "0.01", "0.01"),
    
    ETC_BNB("ETCBNB", "0.0001", "0.01"),
    
    ICX_USDT("ICXUSDT", "0.001", "0.1"),
    
    SC_BTC("SCBTC", "0.00000001", "1"),
    
    SC_ETH("SCETH", "0.00000001", "1"),
    
    SC_BNB("SCBNB", "0.00000001", "1"),
    
    KEY_ETH("KEYETH", "0.00000001", "1"),
    
    NAS_BTC("NASBTC", "0.0000001", "1"),
    
    NAS_ETH("NASETH", "0.0000001", "1"),
    
    MFT_ETH("MFTETH", "0.00000001", "1"),
    
    MFT_BNB("MFTBNB", "0.00000001", "1"),
    
    DENT_ETH("DENTETH", "0.00000001", "1"),
    
    ARDR_BTC("ARDRBTC", "0.00000001", "1"),
    
    NULS_USDT("NULSUSDT", "0.0001", "1"),
    
    HOT_ETH("HOTETH", "0.00000001", "1"),
    
    VET_BTC("VETBTC", "0.00000001", "1"),
    
    VET_ETH("VETETH", "0.00000001", "1"),
    
    VET_USDT("VETUSDT", "0.00001", "0.1"),
    
    VET_BNB("VETBNB", "0.0000001", "1"),
    
    DOCK_BTC("DOCKBTC", "0.00000001", "1"),
    
    POLY_BTC("POLYBTC", "0.00000001", "1"),
    
    GO_BTC("GOBTC", "0.00000001", "1"),
    
    RVN_BTC("RVNBTC", "0.00000001", "1"),
    
    RVN_BNB("RVNBNB", "0.0000001", "1"),
    
    DCR_BTC("DCRBTC", "0.000001", "0.001"),
    
    MITH_BTC("MITHBTC", "0.00000001", "1"),
    
    MITH_BNB("MITHBNB", "0.0000001", "1"),
    
    REN_BTC("RENBTC", "0.00000001", "1"),
    
    BNB_TUSD("BNBTUSD", "0.1", "0.001"),
    
    XRP_TUSD("XRPTUSD", "0.0001", "1"),
    
    BNB_USDC("BNBUSDC", "0.1", "0.001"),
    
    BTC_USDC("BTCUSDC", "0.01", "0.00001"),
    
    ETH_USDC("ETHUSDC", "0.01", "0.0001"),
    
    XRP_USDC("XRPUSDC", "0.0001", "1"),
    
    EOS_USDC("EOSUSDC", "0.001", "0.1"),
    
    USDC_USDT("USDCUSDT", "0.0001", "1"),
    
    ADA_TUSD("ADATUSD", "0.001", "0.1"),
    
    TRX_TUSD("TRXTUSD", "0.00001", "0.1"),
    
    TRX_XRP("TRXXRP", "0.00001", "0.1"),
    
    LINK_USDT("LINKUSDT", "0.01", "0.01"),
    
    LINK_USDC("LINKUSDC", "0.01", "0.01"),
    
    WAVES_USDT("WAVESUSDT", "0.01", "0.01"),
    
    LTC_USDC("LTCUSDC", "0.1", "0.001"),
    
    TRX_USDC("TRXUSDC", "0.00001", "0.1"),
    
    BTT_BNB("BTTBNB", "0.00000001", "1"),
    
    BTT_USDT("BTTUSDT", "0.000001", "1"),
    
    BTT_TUSD("BTTTUSD", "0.000001", "1"),
    
    BTT_USDC("BTTUSDC", "0.000001", "1"),
    
    ONG_BTC("ONGBTC", "0.00000001", "1"),
    
    ONG_USDT("ONGUSDT", "0.0001", "1"),
    
    HOT_BNB("HOTBNB", "0.00000001", "1"),
    
    HOT_USDT("HOTUSDT", "0.000001", "1"),
    
    ZIL_USDT("ZILUSDT", "0.00001", "0.1"),
    
    ZRX_USDT("ZRXUSDT", "0.0001", "1"),
    
    FET_BNB("FETBNB", "0.000001", "1"),
    
    FET_BTC("FETBTC", "0.00000001", "1"),
    
    FET_USDT("FETUSDT", "0.0001", "1"),
    
    BAT_USDT("BATUSDT", "0.0001", "1"),
    
    XMR_BNB("XMRBNB", "0.0001", "0.001"),
    
    XMR_USDT("XMRUSDT", "0.1", "0.001"),
    
    ZEC_BNB("ZECBNB", "0.0001", "0.001"),
    
    ZEC_USDT("ZECUSDT", "0.1", "0.001"),
    
    ZEC_USDC("ZECUSDC", "0.1", "0.001"),
    
    IOST_BNB("IOSTBNB", "0.00000001", "1"),
    
    IOST_USDT("IOSTUSDT", "0.00001", "1"),
    
    CELR_BNB("CELRBNB", "0.00000001", "1"),
    
    CELR_BTC("CELRBTC", "0.00000001", "1"),
    
    CELR_USDT("CELRUSDT", "0.00001", "0.1"),
    
    ADA_USDC("ADAUSDC", "0.001", "0.1"),
    
    DASH_BNB("DASHBNB", "0.0001", "0.001"),
    
    DASH_USDT("DASHUSDT", "0.1", "0.001"),
    
    NANO_USDT("NANOUSDT", "0.001", "0.1"),
    
    OMG_USDT("OMGUSDT", "0.001", "0.1"),
    
    THETA_USDT("THETAUSDT", "0.001", "0.1"),
    
    ENJ_USDT("ENJUSDT", "0.001", "0.1"),
    
    MITH_USDT("MITHUSDT", "0.00001", "0.1"),
    
    MATIC_BNB("MATICBNB", "0.000001", "0.1"),
    
    MATIC_BTC("MATICBTC", "0.00000001", "0.1"),
    
    MATIC_USDT("MATICUSDT", "0.001", "0.1"),
    
    ATOM_BNB("ATOMBNB", "0.00001", "0.01"),
    
    ATOM_BTC("ATOMBTC", "0.0000001", "0.01"),
    
    ATOM_USDT("ATOMUSDT", "0.01", "0.01"),
    
    ATOM_USDC("ATOMUSDC", "0.01", "0.01"),
    
    PHB_BTC("PHBBTC", "0.00000001", "0.1"),
    
    PHB_TUSD("PHBTUSD", "0.001", "0.1"),
    
    TFUEL_BTC("TFUELBTC", "0.00000001", "1"),
    
    TFUEL_USDT("TFUELUSDT", "0.0001", "1"),
    
    ONE_BNB("ONEBNB", "0.0000001", "1"),
    
    ONE_BTC("ONEBTC", "0.00000001", "1"),
    
    ONE_USDT("ONEUSDT", "0.00001", "0.1"),
    
    FTM_BNB("FTMBNB", "0.0000001", "1"),
    
    FTM_BTC("FTMBTC", "0.00000001", "1"),
    
    FTM_USDT("FTMUSDT", "0.0001", "1"),
    
    ALGO_BNB("ALGOBNB", "0.000001", "1"),
    
    ALGO_BTC("ALGOBTC", "0.00000001", "1"),
    
    ALGO_USDT("ALGOUSDT", "0.0001", "1"),
    
    GTO_USDT("GTOUSDT", "0.00001", "0.1"),
    
    DOGE_BTC("DOGEBTC", "0.00000001", "1"),
    
    DOGE_USDT("DOGEUSDT", "0.0001", "1"),
    
    DUSK_BTC("DUSKBTC", "0.00000001", "1"),
    
    DUSK_USDT("DUSKUSDT", "0.0001", "1"),
    
    ANKR_BNB("ANKRBNB", "0.0000001", "1"),
    
    ANKR_BTC("ANKRBTC", "0.00000001", "1"),
    
    ANKR_USDT("ANKRUSDT", "0.00001", "0.1"),
    
    WIN_BNB("WINBNB", "0.00000001", "1"),
    
    WIN_USDT("WINUSDT", "0.0000001", "1"),
    
    WIN_USDC("WINUSDC", "0.0000001", "1"),
    
    COS_BNB("COSBNB", "0.00000001", "1"),
    
    COS_BTC("COSBTC", "0.00000001", "1"),
    
    COS_USDT("COSUSDT", "0.00001", "0.1"),
    
    COCOS_BNB("COCOSBNB", "0.000001", "1"),
    
    COCOS_USDT("COCOSUSDT", "0.0001", "1"),
    
    MTL_USDT("MTLUSDT", "0.001", "0.1"),
    
    TOMO_BTC("TOMOBTC", "0.00000001", "0.1"),
    
    TOMO_USDT("TOMOUSDT", "0.001", "0.1"),
    
    PERL_BNB("PERLBNB", "0.0000001", "1"),
    
    PERL_BTC("PERLBTC", "0.00000001", "1"),
    
    PERL_USDT("PERLUSDT", "0.00001", "0.1"),
    
    DENT_USDT("DENTUSDT", "0.000001", "1"),
    
    MFT_USDT("MFTUSDT", "0.000001", "1"),
    
    KEY_USDT("KEYUSDT", "0.000001", "1"),
    
    DOCK_USDT("DOCKUSDT", "0.00001", "1"),
    
    WAN_USDT("WANUSDT", "0.0001", "1"),
    
    FUN_USDT("FUNUSDT", "0.00001", "1"),
    
    CVC_USDT("CVCUSDT", "0.0001", "1"),
    
    BTT_TRX("BTTTRX", "0.00001", "0.1"),
    
    WIN_TRX("WINTRX", "0.000001", "0.1"),
    
    CHZ_BNB("CHZBNB", "0.0000001", "1"),
    
    CHZ_BTC("CHZBTC", "0.00000001", "1"),
    
    CHZ_USDT("CHZUSDT", "0.0001", "1"),
    
    BAND_BTC("BANDBTC", "0.0000001", "0.1"),
    
    BAND_USDT("BANDUSDT", "0.001", "0.1"),
    
    BNB_BUSD("BNBBUSD", "0.1", "0.001"),
    
    BTC_BUSD("BTCBUSD", "0.01", "0.00001"),
    
    BUSD_USDT("BUSDUSDT", "0.0001", "1"),
    
    BEAM_BTC("BEAMBTC", "0.00000001", "1"),
    
    BEAM_USDT("BEAMUSDT", "0.0001", "1"),
    
    XTZ_BNB("XTZBNB", "0.000001", "0.1"),
    
    XTZ_BTC("XTZBTC", "0.00000001", "0.1"),
    
    XTZ_USDT("XTZUSDT", "0.001", "0.1"),
    
    REN_USDT("RENUSDT", "0.0001", "1"),
    
    RVN_USDT("RVNUSDT", "0.00001", "0.1"),
    
    HBAR_BNB("HBARBNB", "0.0000001", "1"),
    
    HBAR_BTC("HBARBTC", "0.00000001", "1"),
    
    HBAR_USDT("HBARUSDT", "0.0001", "1"),
    
    NKN_BTC("NKNBTC", "0.00000001", "1"),
    
    NKN_USDT("NKNUSDT", "0.0001", "1"),
    
    XRP_BUSD("XRPBUSD", "0.0001", "1"),
    
    ETH_BUSD("ETHBUSD", "0.01", "0.0001"),
    
    LTC_BUSD("LTCBUSD", "0.1", "0.001"),
    
    LINK_BUSD("LINKBUSD", "0.01", "0.01"),
    
    ETC_BUSD("ETCBUSD", "0.01", "0.01"),
    
    STX_BNB("STXBNB", "0.000001", "0.1"),
    
    STX_BTC("STXBTC", "0.00000001", "0.1"),
    
    STX_USDT("STXUSDT", "0.001", "0.1"),
    
    KAVA_BNB("KAVABNB", "0.00001", "0.1"),
    
    KAVA_BTC("KAVABTC", "0.0000001", "0.1"),
    
    KAVA_USDT("KAVAUSDT", "0.001", "0.1"),
    
    BTC_NGN("BTCNGN", "1", "0.00001"),
    
    ARPA_BNB("ARPABNB", "0.0000001", "1"),
    
    ARPA_BTC("ARPABTC", "0.00000001", "1"),
    
    ARPA_USDT("ARPAUSDT", "0.00001", "0.1"),
    
    TRX_BUSD("TRXBUSD", "0.00001", "0.1"),
    
    EOS_BUSD("EOSBUSD", "0.001", "0.1"),
    
    IOTX_USDT("IOTXUSDT", "0.00001", "1"),
    
    RLC_USDT("RLCUSDT", "0.001", "0.1"),
    
    XLM_BUSD("XLMBUSD", "0.0001", "1"),
    
    ADA_BUSD("ADABUSD", "0.001", "0.1"),
    
    CTXC_BTC("CTXCBTC", "0.00000001", "1"),
    
    CTXC_USDT("CTXCUSDT", "0.0001", "1"),
    
    BCH_BNB("BCHBNB", "0.001", "0.001"),
    
    BCH_BTC("BCHBTC", "0.00001", "0.001"),
    
    BCH_USDT("BCHUSDT", "0.1", "0.001"),
    
    BCH_USDC("BCHUSDC", "0.1", "0.001"),
    
    BCH_BUSD("BCHBUSD", "0.1", "0.001"),
    
    BTC_RUB("BTCRUB", "1", "0.00001"),
    
    ETH_RUB("ETHRUB", "0.1", "0.0001"),
    
    XRP_RUB("XRPRUB", "0.01", "1"),
    
    BNB_RUB("BNBRUB", "0.01", "0.001"),
    
    TROY_BNB("TROYBNB", "0.00000001", "1"),
    
    TROY_USDT("TROYUSDT", "0.000001", "1"),
    
    BUSD_RUB("BUSDRUB", "0.01", "1"),
    
    QTUM_BUSD("QTUMBUSD", "0.001", "0.1"),
    
    VET_BUSD("VETBUSD", "0.00001", "0.1"),
    
    VITE_BTC("VITEBTC", "0.00000001", "1"),
    
    VITE_USDT("VITEUSDT", "0.00001", "0.1"),
    
    FTT_BNB("FTTBNB", "0.0001", "0.01"),
    
    FTT_BTC("FTTBTC", "0.0000001", "0.01"),
    
    FTT_USDT("FTTUSDT", "0.01", "0.01"),
    
    BTC_TRY("BTCTRY", "1", "0.00001"),
    
    BNB_TRY("BNBTRY", "1", "0.001"),
    
    BUSD_TRY("BUSDTRY", "0.001", "1"),
    
    ETH_TRY("ETHTRY", "1", "0.0001"),
    
    XRP_TRY("XRPTRY", "0.001", "1"),
    
    USDT_TRY("USDTTRY", "0.001", "1"),
    
    USDT_RUB("USDTRUB", "0.01", "1"),
    
    BTC_EUR("BTCEUR", "0.01", "0.00001"),
    
    ETH_EUR("ETHEUR", "0.01", "0.0001"),
    
    BNB_EUR("BNBEUR", "0.1", "0.001"),
    
    XRP_EUR("XRPEUR", "0.0001", "1"),
    
    EUR_BUSD("EURBUSD", "0.001", "0.1"),
    
    EUR_USDT("EURUSDT", "0.001", "0.1"),
    
    OGN_BNB("OGNBNB", "0.000001", "1"),
    
    OGN_BTC("OGNBTC", "0.00000001", "1"),
    
    OGN_USDT("OGNUSDT", "0.0001", "1"),
    
    DREP_BTC("DREPBTC", "0.00000001", "1"),
    
    DREP_USDT("DREPUSDT", "0.0001", "1"),
    
    TCT_BTC("TCTBTC", "0.00000001", "1"),
    
    TCT_USDT("TCTUSDT", "0.00001", "1"),
    
    WRX_BNB("WRXBNB", "0.000001", "0.1"),
    
    WRX_BTC("WRXBTC", "0.00000001", "0.1"),
    
    WRX_USDT("WRXUSDT", "0.001", "0.1"),
    
    ICX_BUSD("ICXBUSD", "0.001", "0.1"),
    
    BTS_USDT("BTSUSDT", "0.00001", "0.1"),
    
    LSK_USDT("LSKUSDT", "0.001", "0.1"),
    
    BNT_USDT("BNTUSDT", "0.001", "0.1"),
    
    BNT_BUSD("BNTBUSD", "0.001", "0.1"),
    
    LTO_BTC("LTOBTC", "0.00000001", "1"),
    
    LTO_USDT("LTOUSDT", "0.0001", "1"),
    
    ATOM_BUSD("ATOMBUSD", "0.01", "0.01"),
    
    DASH_BUSD("DASHBUSD", "0.1", "0.001"),
    
    NEO_BUSD("NEOBUSD", "0.01", "0.01"),
    
    WAVES_BUSD("WAVESBUSD", "0.01", "0.01"),
    
    XTZ_BUSD("XTZBUSD", "0.001", "0.1"),
    
    BAT_BUSD("BATBUSD", "0.0001", "1"),
    
    ENJ_BUSD("ENJBUSD", "0.001", "0.1"),
    
    NANO_BUSD("NANOBUSD", "0.001", "0.1"),
    
    ONT_BUSD("ONTBUSD", "0.0001", "1"),
    
    RVN_BUSD("RVNBUSD", "0.00001", "0.1"),
    
    AION_USDT("AIONUSDT", "0.0001", "1"),
    
    MBL_BNB("MBLBNB", "0.00000001", "1"),
    
    MBL_USDT("MBLUSDT", "0.000001", "1"),
    
    COTI_BNB("COTIBNB", "0.0000001", "1"),
    
    COTI_BTC("COTIBTC", "0.00000001", "1"),
    
    COTI_USDT("COTIUSDT", "0.0001", "1"),
    
    ALGO_BUSD("ALGOBUSD", "0.0001", "1"),
    
    BTT_BUSD("BTTBUSD", "0.000001", "1"),
    
    TOMO_BUSD("TOMOBUSD", "0.001", "0.1"),
    
    XMR_BUSD("XMRBUSD", "0.1", "0.001"),
    
    ZEC_BUSD("ZECBUSD", "0.1", "0.001"),
    
    STPT_BTC("STPTBTC", "0.00000001", "1"),
    
    STPT_USDT("STPTUSDT", "0.00001", "0.1"),
    
    WTC_USDT("WTCUSDT", "0.0001", "1"),
    
    DATA_BUSD("DATABUSD", "0.00001", "0.1"),
    
    DATA_USDT("DATAUSDT", "0.00001", "0.1"),
    
    SOL_BNB("SOLBNB", "0.0001", "0.01"),
    
    SOL_BTC("SOLBTC", "0.0000001", "0.01"),
    
    SOL_USDT("SOLUSDT", "0.01", "0.01"),
    
    SOL_BUSD("SOLBUSD", "0.01", "0.01"),
    
    BNB_IDRT("BNBIDRT", "1", "0.001"),
    
    USDT_IDRT("USDTIDRT", "1", "1"),
    
    CTSI_BTC("CTSIBTC", "0.00000001", "1"),
    
    CTSI_USDT("CTSIUSDT", "0.0001", "1"),
    
    CTSI_BNB("CTSIBNB", "0.000001", "1"),
    
    CTSI_BUSD("CTSIBUSD", "0.0001", "1"),
    
    HIVE_BTC("HIVEBTC", "0.00000001", "1"),
    
    HIVE_USDT("HIVEUSDT", "0.0001", "1"),
    
    CHR_BNB("CHRBNB", "0.0000001", "1"),
    
    CHR_BTC("CHRBTC", "0.00000001", "1"),
    
    CHR_USDT("CHRUSDT", "0.0001", "1"),
    
    BTCUP_USDT("BTCUPUSDT", "0.001", "0.01"),
    
    BTCDOWN_USDT("BTCDOWNUSDT", "0.000001", "0.01"),
    
    GXS_USDT("GXSUSDT", "0.0001", "1"),
    
    ARDR_USDT("ARDRUSDT", "0.0001", "1"),
    
    HBAR_BUSD("HBARBUSD", "0.0001", "1"),
    
    MATIC_BUSD("MATICBUSD", "0.001", "0.1"),
    
    WRX_BUSD("WRXBUSD", "0.001", "0.1"),
    
    ZIL_BUSD("ZILBUSD", "0.00001", "0.1"),
    
    MDT_BTC("MDTBTC", "0.00000001", "1"),
    
    MDT_USDT("MDTUSDT", "0.00001", "0.1"),
    
    STMX_BNB("STMXBNB", "0.00000001", "1"),
    
    STMX_BTC("STMXBTC", "0.00000001", "1"),
    
    STMX_ETH("STMXETH", "0.00000001", "1"),
    
    STMX_USDT("STMXUSDT", "0.00001", "1"),
    
    KNC_BUSD("KNCBUSD", "0.001", "0.1"),
    
    KNC_USDT("KNCUSDT", "0.001", "0.1"),
    
    REP_USDT("REPUSDT", "0.01", "0.01"),
    
    LRC_BUSD("LRCBUSD", "0.0001", "1"),
    
    LRC_USDT("LRCUSDT", "0.0001", "1"),
    
    IQ_BNB("IQBNB", "0.00000001", "1"),
    
    IQ_BUSD("IQBUSD", "0.00001", "1"),
    
    PNT_BTC("PNTBTC", "0.00000001", "1"),
    
    PNT_USDT("PNTUSDT", "0.0001", "1"),
    
    BTC_GBP("BTCGBP", "0.01", "0.00001"),
    
    ETH_GBP("ETHGBP", "0.01", "0.0001"),
    
    XRP_GBP("XRPGBP", "0.0001", "1"),
    
    BNB_GBP("BNBGBP", "0.1", "0.001"),
    
    GBP_BUSD("GBPBUSD", "0.001", "0.1"),
    
    DGB_BNB("DGBBNB", "0.0000001", "1"),
    
    DGB_BTC("DGBBTC", "0.00000001", "1"),
    
    DGB_BUSD("DGBBUSD", "0.00001", "0.1"),
    
    BTC_UAH("BTCUAH", "1", "0.00001"),
    
    USDT_UAH("USDTUAH", "0.01", "1"),
    
    COMP_BTC("COMPBTC", "0.00001", "0.001"),
    
    COMP_BUSD("COMPBUSD", "0.1", "0.001"),
    
    COMP_USDT("COMPUSDT", "0.1", "0.001"),
    
    BTC_BIDR("BTCBIDR", "1", "0.00001"),
    
    ETH_BIDR("ETHBIDR", "1", "0.0001"),
    
    BNB_BIDR("BNBBIDR", "1", "0.001"),
    
    BUSD_BIDR("BUSDBIDR", "1", "0.1"),
    
    USDT_BIDR("USDTBIDR", "1", "0.1"),
    
    SC_USDT("SCUSDT", "0.00001", "1"),
    
    ZEN_USDT("ZENUSDT", "0.01", "0.01"),
    
    SXP_BTC("SXPBTC", "0.00000001", "0.1"),
    
    SXP_BNB("SXPBNB", "0.000001", "0.1"),
    
    SXP_BUSD("SXPBUSD", "0.001", "0.1"),
    
    SNX_BTC("SNXBTC", "0.0000001", "0.1"),
    
    SNX_BNB("SNXBNB", "0.00001", "0.1"),
    
    SNX_BUSD("SNXBUSD", "0.001", "0.1"),
    
    SNX_USDT("SNXUSDT", "0.001", "0.1"),
    
    ETHUP_USDT("ETHUPUSDT", "0.001", "0.01"),
    
    ETHDOWN_USDT("ETHDOWNUSDT", "0.0001", "0.01"),
    
    ADAUP_USDT("ADAUPUSDT", "0.001", "0.01"),
    
    ADADOWN_USDT("ADADOWNUSDT", "0.000001", "0.01"),
    
    LINKUP_USDT("LINKUPUSDT", "0.0001", "0.01"),
    
    LINKDOWN_USDT("LINKDOWNUSDT", "0.000001", "0.01"),
    
    VTHO_BNB("VTHOBNB", "0.00000001", "1"),
    
    VTHO_USDT("VTHOUSDT", "0.000001", "1"),
    
    DGB_USDT("DGBUSDT", "0.00001", "0.1"),
    
    GBP_USDT("GBPUSDT", "0.001", "0.1"),
    
    STORJ_BUSD("STORJBUSD", "0.0001", "1"),
    
    SXP_USDT("SXPUSDT", "0.001", "0.1"),
    
    IRIS_BTC("IRISBTC", "0.00000001", "1"),
    
    MKR_BTC("MKRBTC", "0.00001", "0.0001"),
    
    MKR_USDT("MKRUSDT", "1", "0.0001"),
    
    MKR_BUSD("MKRBUSD", "1", "0.0001"),
    
    RUNE_BNB("RUNEBNB", "0.00001", "0.1"),
    
    RUNE_BTC("RUNEBTC", "0.0000001", "0.1"),
    
    RUNE_BUSD("RUNEBUSD", "0.001", "0.1"),
    
    MANA_BUSD("MANABUSD", "0.0001", "1"),
    
    DOGE_BUSD("DOGEBUSD", "0.0001", "1"),
    
    ZRX_BUSD("ZRXBUSD", "0.0001", "1"),
    
    DCR_USDT("DCRUSDT", "0.1", "0.001"),
    
    STORJ_USDT("STORJUSDT", "0.0001", "1"),
    
    BTC_AUD("BTCAUD", "0.01", "0.00001"),
    
    ETH_AUD("ETHAUD", "0.01", "0.0001"),
    
    AUD_BUSD("AUDBUSD", "0.0001", "1"),
    
    FIO_BNB("FIOBNB", "0.0000001", "1"),
    
    FIO_BTC("FIOBTC", "0.00000001", "1"),
    
    FIO_BUSD("FIOBUSD", "0.0001", "1"),
    
    BNBUP_USDT("BNBUPUSDT", "0.01", "0.01"),
    
    BNBDOWN_USDT("BNBDOWNUSDT", "0.00001", "0.01"),
    
    XTZUP_USDT("XTZUPUSDT", "0.00001", "0.01"),
    
    XTZDOWN_USDT("XTZDOWNUSDT", "0.001", "0.01"),
    
    AVA_BNB("AVABNB", "0.000001", "0.1"),
    
    AVA_BTC("AVABTC", "0.00000001", "0.1"),
    
    AVA_BUSD("AVABUSD", "0.001", "0.1"),
    
    IOTA_BUSD("IOTABUSD", "0.0001", "1"),
    
    MANA_USDT("MANAUSDT", "0.0001", "1"),
    
    XRP_AUD("XRPAUD", "0.0001", "1"),
    
    BNB_AUD("BNBAUD", "0.1", "0.001"),
    
    AUD_USDT("AUDUSDT", "0.0001", "1"),
    
    BAL_BTC("BALBTC", "0.0000001", "0.01"),
    
    BAL_BUSD("BALBUSD", "0.01", "0.01"),
    
    YFI_BTC("YFIBTC", "0.0001", "0.00001"),
    
    YFI_BUSD("YFIBUSD", "0.01", "0.00001"),
    
    YFI_USDT("YFIUSDT", "0.01", "0.00001"),
    
    BAL_USDT("BALUSDT", "0.01", "0.01"),
    
    BLZ_USDT("BLZUSDT", "0.0001", "1"),
    
    IRIS_USDT("IRISUSDT", "0.00001", "0.1"),
    
    KMD_USDT("KMDUSDT", "0.0001", "1"),
    
    BTC_DAI("BTCDAI", "0.01", "0.00001"),
    
    ETH_DAI("ETHDAI", "0.01", "0.0001"),
    
    BNB_DAI("BNBDAI", "0.1", "0.001"),
    
    USDT_DAI("USDTDAI", "0.0001", "0.1"),
    
    BUSD_DAI("BUSDDAI", "0.0001", "0.1"),
    
    JST_BTC("JSTBTC", "0.00000001", "1"),
    
    JST_BUSD("JSTBUSD", "0.00001", "0.1"),
    
    JST_USDT("JSTUSDT", "0.00001", "0.1"),
    
    SRM_BNB("SRMBNB", "0.00001", "0.1"),
    
    SRM_BTC("SRMBTC", "0.0000001", "0.1"),
    
    SRM_BUSD("SRMBUSD", "0.001", "0.1"),
    
    SRM_USDT("SRMUSDT", "0.001", "0.1"),
    
    ANT_BNB("ANTBNB", "0.00001", "0.1"),
    
    ANT_BTC("ANTBTC", "0.00000001", "0.1"),
    
    ANT_BUSD("ANTBUSD", "0.001", "0.1"),
    
    ANT_USDT("ANTUSDT", "0.001", "0.1"),
    
    CRV_BTC("CRVBTC", "0.00000001", "0.1"),
    
    CRV_BUSD("CRVBUSD", "0.001", "0.1"),
    
    CRV_USDT("CRVUSDT", "0.001", "0.1"),
    
    SAND_BNB("SANDBNB", "0.000001", "1"),
    
    SAND_BTC("SANDBTC", "0.00000001", "1"),
    
    SAND_USDT("SANDUSDT", "0.0001", "1"),
    
    SAND_BUSD("SANDBUSD", "0.0001", "1"),
    
    OCEAN_BNB("OCEANBNB", "0.000001", "1"),
    
    OCEAN_BTC("OCEANBTC", "0.00000001", "1"),
    
    OCEAN_BUSD("OCEANBUSD", "0.0001", "1"),
    
    OCEAN_USDT("OCEANUSDT", "0.0001", "1"),
    
    NMR_BNB("NMRBNB", "0.0001", "0.01"),
    
    NMR_BTC("NMRBTC", "0.000001", "0.01"),
    
    NMR_BUSD("NMRBUSD", "0.01", "0.01"),
    
    NMR_USDT("NMRUSDT", "0.01", "0.01"),
    
    DOT_BNB("DOTBNB", "0.00001", "0.01"),
    
    DOT_BTC("DOTBTC", "0.0000001", "0.01"),
    
    DOT_BUSD("DOTBUSD", "0.01", "0.01"),
    
    DOT_USDT("DOTUSDT", "0.01", "0.01"),
    
    LUNA_BNB("LUNABNB", "0.00001", "0.01"),
    
    LUNA_BTC("LUNABTC", "0.0000001", "0.01"),
    
    LUNA_BUSD("LUNABUSD", "0.01", "0.01"),
    
    LUNA_USDT("LUNAUSDT", "0.01", "0.01"),
    
    IDEX_BTC("IDEXBTC", "0.00000001", "1"),
    
    IDEX_BUSD("IDEXBUSD", "0.00001", "0.1"),
    
    RSR_BNB("RSRBNB", "0.0000001", "1"),
    
    RSR_BTC("RSRBTC", "0.00000001", "1"),
    
    RSR_BUSD("RSRBUSD", "0.00001", "0.1"),
    
    RSR_USDT("RSRUSDT", "0.00001", "0.1"),
    
    PAXG_BNB("PAXGBNB", "0.001", "0.0001"),
    
    PAXG_BTC("PAXGBTC", "0.00001", "0.0001"),
    
    PAXG_USDT("PAXGUSDT", "1", "0.0001"),
    
    WNXM_BTC("WNXMBTC", "0.000001", "0.01"),
    
    WNXM_USDT("WNXMUSDT", "0.01", "0.01"),
    
    TRB_BTC("TRBBTC", "0.000001", "0.01"),
    
    TRB_BUSD("TRBBUSD", "0.01", "0.01"),
    
    TRB_USDT("TRBUSDT", "0.01", "0.01"),
    
    DOT_BIDR("DOTBIDR", "1", "0.01"),
    
    LINK_AUD("LINKAUD", "0.01", "0.01"),
    
    SXP_AUD("SXPAUD", "0.001", "0.1"),
    
    WBTC_BTC("WBTCBTC", "0.0001", "0.00001"),
    
    WBTC_ETH("WBTCETH", "0.01", "0.00001"),
    
    SUSHI_BNB("SUSHIBNB", "0.00001", "0.1"),
    
    SUSHI_BTC("SUSHIBTC", "0.0000001", "0.1"),
    
    SUSHI_BUSD("SUSHIBUSD", "0.001", "0.1"),
    
    SUSHI_USDT("SUSHIUSDT", "0.001", "0.1"),
    
    YFII_BNB("YFIIBNB", "0.01", "0.0001"),
    
    YFII_BTC("YFIIBTC", "0.0001", "0.0001"),
    
    YFII_BUSD("YFIIBUSD", "1", "0.0001"),
    
    YFII_USDT("YFIIUSDT", "1", "0.0001"),
    
    KSM_BNB("KSMBNB", "0.0001", "0.001"),
    
    KSM_BTC("KSMBTC", "0.000001", "0.001"),
    
    KSM_BUSD("KSMBUSD", "0.1", "0.001"),
    
    KSM_USDT("KSMUSDT", "0.1", "0.001"),
    
    EGLD_BNB("EGLDBNB", "0.0001", "0.01"),
    
    EGLD_BTC("EGLDBTC", "0.000001", "0.01"),
    
    EGLD_BUSD("EGLDBUSD", "0.01", "0.01"),
    
    EGLD_USDT("EGLDUSDT", "0.01", "0.01"),
    
    DIA_BTC("DIABTC", "0.00000001", "0.1"),
    
    DIA_BUSD("DIABUSD", "0.001", "0.1"),
    
    DIA_USDT("DIAUSDT", "0.001", "0.1"),
    
    RUNE_USDT("RUNEUSDT", "0.001", "0.1"),
    
    FIO_USDT("FIOUSDT", "0.0001", "1"),
    
    UMA_BTC("UMABTC", "0.0000001", "0.1"),
    
    UMA_USDT("UMAUSDT", "0.001", "0.1"),
    
    TRXUP_USDT("TRXUPUSDT", "0.00001", "0.01"),
    
    TRXDOWN_USDT("TRXDOWNUSDT", "0.001", "0.01"),
    
    XRPUP_USDT("XRPUPUSDT", "0.0001", "0.01"),
    
    XRPDOWN_USDT("XRPDOWNUSDT", "0.0000001", "0.01"),
    
    DOTUP_USDT("DOTUPUSDT", "0.001", "0.01"),
    
    DOTDOWN_USDT("DOTDOWNUSDT", "0.001", "0.01"),
    
    LINK_TRY("LINKTRY", "0.1", "0.01"),
    
    USDT_NGN("USDTNGN", "0.1", "0.1"),
    
    BEL_BNB("BELBNB", "0.000001", "0.1"),
    
    BEL_BTC("BELBTC", "0.00000001", "0.1"),
    
    BEL_BUSD("BELBUSD", "0.001", "0.1"),
    
    BEL_USDT("BELUSDT", "0.001", "0.1"),
    
    WING_BTC("WINGBTC", "0.0000001", "0.01"),
    
    WING_BUSD("WINGBUSD", "0.01", "0.01"),
    
    WING_USDT("WINGUSDT", "0.01", "0.01"),
    
    LTCUP_USDT("LTCUPUSDT", "0.001", "0.01"),
    
    LTCDOWN_USDT("LTCDOWNUSDT", "0.0001", "0.01"),
    
    SXP_EUR("SXPEUR", "0.001", "0.1"),
    
    CREAM_BNB("CREAMBNB", "0.0001", "0.001"),
    
    CREAM_BUSD("CREAMBUSD", "0.1", "0.001"),
    
    UNI_BNB("UNIBNB", "0.00001", "0.01"),
    
    UNI_BTC("UNIBTC", "0.0000001", "0.01"),
    
    UNI_BUSD("UNIBUSD", "0.01", "0.01"),
    
    UNI_USDT("UNIUSDT", "0.01", "0.01"),
    
    NBS_USDT("NBSUSDT", "0.00001", "0.1"),
    
    OXT_BTC("OXTBTC", "0.00000001", "1"),
    
    OXT_USDT("OXTUSDT", "0.0001", "1"),
    
    SUN_USDT("SUNUSDT", "0.00001", "1"),
    
    AVAX_BNB("AVAXBNB", "0.00001", "0.01"),
    
    AVAX_BTC("AVAXBTC", "0.0000001", "0.01"),
    
    AVAX_BUSD("AVAXBUSD", "0.01", "0.01"),
    
    AVAX_USDT("AVAXUSDT", "0.01", "0.01"),
    
    HNT_BTC("HNTBTC", "0.0000001", "0.01"),
    
    HNT_USDT("HNTUSDT", "0.01", "0.01"),
    
    BAKE_BNB("BAKEBNB", "0.000001", "0.1"),
    
    BURGER_BNB("BURGERBNB", "0.00001", "0.1"),
    
    SXP_BIDR("SXPBIDR", "1", "0.1"),
    
    FLM_BTC("FLMBTC", "0.00000001", "1"),
    
    FLM_BUSD("FLMBUSD", "0.0001", "1"),
    
    FLM_USDT("FLMUSDT", "0.0001", "1"),
    
    SCRT_BTC("SCRTBTC", "0.00000001", "0.1"),
    
    SCRT_ETH("SCRTETH", "0.0000001", "1"),
    
    CAKE_BNB("CAKEBNB", "0.00001", "0.01"),
    
    CAKE_BUSD("CAKEBUSD", "0.01", "0.01"),
    
    SPARTA_BNB("SPARTABNB", "0.0000001", "1"),
    
    ORN_BTC("ORNBTC", "0.0000001", "0.1"),
    
    ORN_USDT("ORNUSDT", "0.001", "0.1"),
    
    SXP_TRY("SXPTRY", "0.01", "0.1"),
    
    UTK_BTC("UTKBTC", "0.00000001", "1"),
    
    UTK_USDT("UTKUSDT", "0.0001", "1"),
    
    XVS_BNB("XVSBNB", "0.00001", "0.01"),
    
    XVS_BTC("XVSBTC", "0.0000001", "0.01"),
    
    XVS_BUSD("XVSBUSD", "0.01", "0.01"),
    
    XVS_USDT("XVSUSDT", "0.01", "0.01"),
    
    ALPHA_BNB("ALPHABNB", "0.000001", "1"),
    
    ALPHA_BTC("ALPHABTC", "0.00000001", "1"),
    
    ALPHA_BUSD("ALPHABUSD", "0.0001", "1"),
    
    ALPHA_USDT("ALPHAUSDT", "0.0001", "1"),
    
    VIDT_BTC("VIDTBTC", "0.00000001", "1"),
    
    VIDT_BUSD("VIDTBUSD", "0.0001", "1"),
    
    AAVE_BNB("AAVEBNB", "0.0001", "0.001"),
    
    BTC_BRL("BTCBRL", "1", "0.00001"),
    
    USDT_BRL("USDTBRL", "0.001", "0.1"),
    
    AAVE_BTC("AAVEBTC", "0.000001", "0.001"),
    
    AAVE_ETH("AAVEETH", "0.0001", "0.001"),
    
    AAVE_BUSD("AAVEBUSD", "0.1", "0.001"),
    
    AAVE_USDT("AAVEUSDT", "0.1", "0.001"),
    
    NEAR_BNB("NEARBNB", "0.000001", "0.1"),
    
    NEAR_BTC("NEARBTC", "0.00000001", "0.1"),
    
    NEAR_BUSD("NEARBUSD", "0.001", "0.1"),
    
    NEAR_USDT("NEARUSDT", "0.001", "0.1"),
    
    SXP_GBP("SXPGBP", "0.001", "0.1"),
    
    FIL_BNB("FILBNB", "0.0001", "0.01"),
    
    FIL_BTC("FILBTC", "0.000001", "0.01"),
    
    FIL_BUSD("FILBUSD", "0.01", "0.01"),
    
    FIL_USDT("FILUSDT", "0.01", "0.01"),
    
    FILUP_USDT("FILUPUSDT", "0.0001", "0.01"),
    
    FILDOWN_USDT("FILDOWNUSDT", "0.0000001", "0.01"),
    
    INJ_BNB("INJBNB", "0.00001", "0.1"),
    
    INJ_BTC("INJBTC", "0.0000001", "0.1"),
    
    INJ_BUSD("INJBUSD", "0.001", "0.1"),
    
    INJ_USDT("INJUSDT", "0.001", "0.1"),
    
    AERGO_BTC("AERGOBTC", "0.00000001", "1"),
    
    AERGO_BUSD("AERGOBUSD", "0.0001", "1"),
    
    LINK_EUR("LINKEUR", "0.01", "0.01"),
    
    ONE_BUSD("ONEBUSD", "0.00001", "0.1"),
    
    AUDIO_BTC("AUDIOBTC", "0.00000001", "0.1"),
    
    AUDIO_BUSD("AUDIOBUSD", "0.001", "0.1"),
    
    AUDIO_USDT("AUDIOUSDT", "0.001", "0.1"),
    
    CTK_BNB("CTKBNB", "0.000001", "0.1"),
    
    CTK_BTC("CTKBTC", "0.00000001", "0.1"),
    
    CTK_BUSD("CTKBUSD", "0.001", "0.1"),
    
    CTK_USDT("CTKUSDT", "0.001", "0.1"),
    
    BCHUP_USDT("BCHUPUSDT", "0.00001", "0.01"),
    
    BCHDOWN_USDT("BCHDOWNUSDT", "0.0001", "0.01"),
    
    ETH_BRL("ETHBRL", "0.01", "0.0001"),
    
    DOT_EUR("DOTEUR", "0.01", "0.01"),
    
    AKRO_BTC("AKROBTC", "0.00000001", "1"),
    
    AKRO_USDT("AKROUSDT", "0.00001", "1"),
    
    KP3R_BNB("KP3RBNB", "0.0001", "0.01"),
    
    KP3R_BUSD("KP3RBUSD", "0.01", "0.01"),
    
    AXS_BNB("AXSBNB", "0.0001", "0.01"),
    
    AXS_BTC("AXSBTC", "0.000001", "0.01"),
    
    AXS_BUSD("AXSBUSD", "0.01", "0.01"),
    
    AXS_USDT("AXSUSDT", "0.01", "0.01"),
    
    HARD_BNB("HARDBNB", "0.000001", "1"),
    
    HARD_BTC("HARDBTC", "0.00000001", "1"),
    
    HARD_BUSD("HARDBUSD", "0.0001", "1"),
    
    HARD_USDT("HARDUSDT", "0.0001", "1"),
    
    BNB_BRL("BNBBRL", "1", "0.001"),
    
    LTC_EUR("LTCEUR", "0.1", "0.001"),
    
    RENBTC_BTC("RENBTCBTC", "0.0001", "0.00001"),
    
    DNT_BUSD("DNTBUSD", "0.0001", "1"),
    
    DNT_USDT("DNTUSDT", "0.0001", "1"),
    
    SLP_ETH("SLPETH", "0.00000001", "1"),
    
    ADA_EUR("ADAEUR", "0.001", "0.1"),
    
    CVP_ETH("CVPETH", "0.0000001", "1"),
    
    CVP_BUSD("CVPBUSD", "0.001", "0.1"),
    
    STRAX_BTC("STRAXBTC", "0.00000001", "0.1"),
    
    STRAX_ETH("STRAXETH", "0.0000001", "0.1"),
    
    STRAX_BUSD("STRAXBUSD", "0.001", "0.1"),
    
    STRAX_USDT("STRAXUSDT", "0.001", "0.1"),
    
    FOR_BTC("FORBTC", "0.00000001", "1"),
    
    FOR_BUSD("FORBUSD", "0.00001", "0.1"),
    
    UNFI_BTC("UNFIBTC", "0.0000001", "0.1"),
    
    UNFI_BUSD("UNFIBUSD", "0.001", "0.1"),
    
    UNFI_USDT("UNFIUSDT", "0.001", "0.1"),
    
    FRONT_BUSD("FRONTBUSD", "0.0001", "1"),
    
    ROSE_BTC("ROSEBTC", "0.00000001", "1"),
    
    ROSE_BUSD("ROSEBUSD", "0.00001", "0.1"),
    
    ROSE_USDT("ROSEUSDT", "0.00001", "0.1"),
    
    AVAX_TRY("AVAXTRY", "0.1", "0.01"),
    
    BUSD_BRL("BUSDBRL", "0.001", "0.1"),
    
    AVA_USDT("AVAUSDT", "0.001", "0.1"),
    
    SYS_BUSD("SYSBUSD", "0.0001", "1"),
    
    XEM_USDT("XEMUSDT", "0.0001", "1"),
    
    HEGIC_ETH("HEGICETH", "0.00000001", "1"),
    
    HEGIC_BUSD("HEGICBUSD", "0.0001", "1"),
    
    PROM_BNB("PROMBNB", "0.00001", "0.01"),
    
    PROM_BUSD("PROMBUSD", "0.01", "0.01"),
    
    XRP_BRL("XRPBRL", "0.001", "1"),
    
    SKL_BTC("SKLBTC", "0.00000001", "1"),
    
    SKL_BUSD("SKLBUSD", "0.0001", "1"),
    
    SKL_USDT("SKLUSDT", "0.0001", "1"),
    
    BCH_EUR("BCHEUR", "0.1", "0.001"),
    
    YFI_EUR("YFIEUR", "0.01", "0.00001"),
    
    ZIL_BIDR("ZILBIDR", "1", "1"),
    
    SUSD_USDT("SUSDUSDT", "0.001", "0.1"),
    
    GLM_BTC("GLMBTC", "0.00000001", "1"),
    
    GLM_ETH("GLMETH", "0.0000001", "1"),
    
    GHST_ETH("GHSTETH", "0.0000001", "0.1"),
    
    GHST_BUSD("GHSTBUSD", "0.001", "0.1"),
    
    LINK_BRL("LINKBRL", "0.1", "0.01"),
    
    LTC_RUB("LTCRUB", "0.1", "0.001"),
    
    TRX_TRY("TRXTRY", "0.0001", "1"),
    
    XLM_EUR("XLMEUR", "0.0001", "1"),
    
    DF_BUSD("DFBUSD", "0.0001", "1"),
    
    GRT_BTC("GRTBTC", "0.00000001", "1"),
    
    GRT_ETH("GRTETH", "0.0000001", "1"),
    
    GRT_USDT("GRTUSDT", "0.0001", "1"),
    
    JUV_BTC("JUVBTC", "0.0000001", "0.01"),
    
    JUV_BUSD("JUVBUSD", "0.01", "0.01"),
    
    JUV_USDT("JUVUSDT", "0.01", "0.01"),
    
    PSG_BTC("PSGBTC", "0.0000001", "0.01"),
    
    PSG_BUSD("PSGBUSD", "0.01", "0.01"),
    
    PSG_USDT("PSGUSDT", "0.01", "0.01"),
    
    $1INCH_BTC("1INCHBTC", "0.00000001", "0.1"),
    
    $1INCH_USDT("1INCHUSDT", "0.001", "0.1"),
    
    REEF_BTC("REEFBTC", "0.00000001", "1"),
    
    REEF_USDT("REEFUSDT", "0.00001", "1"),
    
    OG_BTC("OGBTC", "0.0000001", "0.1"),
    
    OG_USDT("OGUSDT", "0.001", "0.1"),
    
    ATM_BTC("ATMBTC", "0.0000001", "0.01"),
    
    ATM_USDT("ATMUSDT", "0.01", "0.01"),
    
    ASR_BTC("ASRBTC", "0.0000001", "0.1"),
    
    ASR_USDT("ASRUSDT", "0.001", "0.1"),
    
    CELO_BTC("CELOBTC", "0.00000001", "0.1"),
    
    CELO_USDT("CELOUSDT", "0.001", "0.1"),
    
    RIF_BTC("RIFBTC", "0.00000001", "1"),
    
    RIF_USDT("RIFUSDT", "0.0001", "1"),
    
    CHZ_TRY("CHZTRY", "0.001", "1"),
    
    XLM_TRY("XLMTRY", "0.001", "1"),
    
    LINK_GBP("LINKGBP", "0.01", "0.01"),
    
    GRT_EUR("GRTEUR", "0.0001", "1"),
    
    BTCST_BTC("BTCSTBTC", "0.0000001", "0.01"),
    
    BTCST_BUSD("BTCSTBUSD", "0.01", "0.01"),
    
    BTCST_USDT("BTCSTUSDT", "0.01", "0.01"),
    
    TRU_BTC("TRUBTC", "0.00000001", "1"),
    
    TRU_USDT("TRUUSDT", "0.0001", "1"),
    
    DEXE_ETH("DEXEETH", "0.000001", "0.01"),
    
    DEXE_BUSD("DEXEBUSD", "0.01", "0.01"),
    
    EOS_EUR("EOSEUR", "0.001", "0.1"),
    
    LTC_BRL("LTCBRL", "0.1", "0.001"),
    
    USDC_BUSD("USDCBUSD", "0.0001", "0.01"),
    
    TUSD_BUSD("TUSDBUSD", "0.0001", "0.1"),
    
    CKB_BTC("CKBBTC", "0.00000001", "1"),
    
    CKB_BUSD("CKBBUSD", "0.00001", "1"),
    
    CKB_USDT("CKBUSDT", "0.00001", "1"),
    
    TWT_BTC("TWTBTC", "0.00000001", "1"),
    
    TWT_BUSD("TWTBUSD", "0.0001", "1"),
    
    TWT_USDT("TWTUSDT", "0.0001", "1"),
    
    FIRO_BTC("FIROBTC", "0.0000001", "0.1"),
    
    FIRO_ETH("FIROETH", "0.000001", "0.1"),
    
    FIRO_USDT("FIROUSDT", "0.001", "0.1"),
    
    BETH_ETH("BETHETH", "0.0001", "0.0001"),
    
    DOGE_EUR("DOGEEUR", "0.0001", "1"),
    
    DOGE_TRY("DOGETRY", "0.001", "1"),
    
    DOGE_AUD("DOGEAUD", "0.0001", "1"),
    
    DOGE_BRL("DOGEBRL", "0.001", "1"),
    
    PROS_ETH("PROSETH", "0.0000001", "1"),
    
    LIT_BTC("LITBTC", "0.00000001", "0.1"),
    
    LIT_BUSD("LITBUSD", "0.001", "0.1"),
    
    LIT_USDT("LITUSDT", "0.001", "0.1"),
    
    BUSD_VAI("BUSDVAI", "0.001", "0.1"),
    
    SFP_BTC("SFPBTC", "0.00000001", "1"),
    
    SFP_BUSD("SFPBUSD", "0.0001", "1"),
    
    SFP_USDT("SFPUSDT", "0.0001", "1"),
    
    DOGE_GBP("DOGEGBP", "0.0001", "1"),
    
    DOT_TRY("DOTTRY", "0.1", "0.01"),
    
    FXS_BTC("FXSBTC", "0.00000001", "0.1"),
    
    FXS_BUSD("FXSBUSD", "0.001", "0.1"),
    
    DODO_BTC("DODOBTC", "0.00000001", "0.1"),
    
    DODO_BUSD("DODOBUSD", "0.001", "0.1"),
    
    DODO_USDT("DODOUSDT", "0.001", "0.1"),
    
    FRONT_BTC("FRONTBTC", "0.00000001", "1"),
    
    CAKE_BTC("CAKEBTC", "0.0000001", "0.01"),
    
    CAKE_USDT("CAKEUSDT", "0.01", "0.01"),
    
    BAKE_BUSD("BAKEBUSD", "0.001", "0.1"),
    
    UFT_ETH("UFTETH", "0.0000001", "1"),
    
    UFT_BUSD("UFTBUSD", "0.0001", "1"),
    
    $1INCH_BUSD("1INCHBUSD", "0.001", "0.1"),
    
    BAND_BUSD("BANDBUSD", "0.001", "0.1"),
    
    GRT_BUSD("GRTBUSD", "0.0001", "1"),
    
    IOST_BUSD("IOSTBUSD", "0.00001", "0.1"),
    
    OMG_BUSD("OMGBUSD", "0.001", "0.1"),
    
    REEF_BUSD("REEFBUSD", "0.00001", "0.1"),
    
    ACM_BTC("ACMBTC", "0.0000001", "0.1"),
    
    ACM_BUSD("ACMBUSD", "0.001", "0.1"),
    
    ACM_USDT("ACMUSDT", "0.001", "0.1"),
    
    AUCTION_BTC("AUCTIONBTC", "0.0000001", "0.01"),
    
    AUCTION_BUSD("AUCTIONBUSD", "0.01", "0.01"),
    
    PHA_BTC("PHABTC", "0.00000001", "1"),
    
    PHA_BUSD("PHABUSD", "0.0001", "1"),
    
    DOT_GBP("DOTGBP", "0.01", "0.01"),
    
    ADA_TRY("ADATRY", "0.01", "0.1"),
    
    ADA_BRL("ADABRL", "0.001", "0.1"),
    
    ADA_GBP("ADAGBP", "0.0001", "0.1"),
    
    TVK_BTC("TVKBTC", "0.00000001", "1"),
    
    TVK_BUSD("TVKBUSD", "0.0001", "1"),
    
    BADGER_BTC("BADGERBTC", "0.0000001", "0.01"),
    
    BADGER_BUSD("BADGERBUSD", "0.01", "0.01"),
    
    BADGER_USDT("BADGERUSDT", "0.01", "0.01"),
    
    FIS_BTC("FISBTC", "0.00000001", "1"),
    
    FIS_BUSD("FISBUSD", "0.0001", "1"),
    
    FIS_USDT("FISUSDT", "0.0001", "1"),
    
    DOT_BRL("DOTBRL", "0.01", "0.01"),
    
    ADA_AUD("ADAAUD", "0.001", "0.1"),
    
    HOT_TRY("HOTTRY", "0.00001", "1"),
    
    EGLD_EUR("EGLDEUR", "0.01", "0.01"),
    
    OM_BTC("OMBTC", "0.00000001", "1"),
    
    OM_BUSD("OMBUSD", "0.0001", "1"),
    
    OM_USDT("OMUSDT", "0.0001", "1"),
    
    POND_BTC("PONDBTC", "0.00000001", "1"),
    
    POND_BUSD("PONDBUSD", "0.00001", "0.01"),
    
    POND_USDT("PONDUSDT", "0.00001", "0.01"),
    
    DEGO_BTC("DEGOBTC", "0.0000001", "0.01"),
    
    DEGO_BUSD("DEGOBUSD", "0.01", "0.01"),
    
    DEGO_USDT("DEGOUSDT", "0.01", "0.01"),
    
    AVAX_EUR("AVAXEUR", "0.01", "0.01"),
    
    BTT_TRY("BTTTRY", "0.00001", "1"),
    
    CHZ_BRL("CHZBRL", "0.001", "1"),
    
    UNI_EUR("UNIEUR", "0.01", "0.01"),
    
    ALICE_BTC("ALICEBTC", "0.0000001", "0.01"),
    
    ALICE_BUSD("ALICEBUSD", "0.01", "0.01"),
    
    ALICE_USDT("ALICEUSDT", "0.01", "0.01"),
    
    CHZ_BUSD("CHZBUSD", "0.0001", "1"),
    
    CHZ_EUR("CHZEUR", "0.0001", "1"),
    
    CHZ_GBP("CHZGBP", "0.0001", "1"),
    
    BIFI_BUSD("BIFIBUSD", "0.1", "0.001"),
    
    LINA_BTC("LINABTC", "0.00000001", "1"),
    
    LINA_BUSD("LINABUSD", "0.00001", "0.01"),
    
    LINA_USDT("LINAUSDT", "0.00001", "0.01"),
    
    ADA_RUB("ADARUB", "0.01", "0.1"),
    
    ENJ_BRL("ENJBRL", "0.001", "0.1"),
    
    ENJ_EUR("ENJEUR", "0.001", "0.1"),
    
    MATIC_EUR("MATICEUR", "0.0001", "0.1"),
    
    NEO_TRY("NEOTRY", "0.1", "0.01"),
    
    PERP_BTC("PERPBTC", "0.0000001", "0.01"),
    
    PERP_BUSD("PERPBUSD", "0.01", "0.01"),
    
    PERP_USDT("PERPUSDT", "0.01", "0.01"),
    
    RAMP_BTC("RAMPBTC", "0.00000001", "1"),
    
    RAMP_BUSD("RAMPBUSD", "0.0001", "1"),
    
    RAMP_USDT("RAMPUSDT", "0.0001", "1"),
    
    SUPER_BTC("SUPERBTC", "0.00000001", "1"),
    
    SUPER_BUSD("SUPERBUSD", "0.0001", "1"),
    
    SUPER_USDT("SUPERUSDT", "0.0001", "1"),
    
    CFX_BTC("CFXBTC", "0.00000001", "1"),
    
    CFX_BUSD("CFXBUSD", "0.0001", "1"),
    
    CFX_USDT("CFXUSDT", "0.0001", "1"),
    
    ENJ_GBP("ENJGBP", "0.0001", "0.1"),
    
    EOS_TRY("EOSTRY", "0.01", "0.1"),
    
    LTC_GBP("LTCGBP", "0.01", "0.001"),
    
    LUNA_EUR("LUNAEUR", "0.01", "0.01"),
    
    RVN_TRY("RVNTRY", "0.0001", "0.01"),
    
    THETA_EUR("THETAEUR", "0.001", "0.1"),
    
    XVG_BUSD("XVGBUSD", "0.00001", "0.1"),
    
    EPS_BTC("EPSBTC", "0.00000001", "1"),
    
    EPS_BUSD("EPSBUSD", "0.0001", "1"),
    
    EPS_USDT("EPSUSDT", "0.0001", "1"),
    
    AUTO_BTC("AUTOBTC", "0.00001", "0.001"),
    
    AUTO_BUSD("AUTOBUSD", "0.1", "0.001"),
    
    AUTO_USDT("AUTOUSDT", "0.1", "0.001"),
    
    TKO_BTC("TKOBTC", "0.00000001", "0.1"),
    
    TKO_BIDR("TKOBIDR", "0.01", "0.1"),
    
    TKO_BUSD("TKOBUSD", "0.001", "0.1"),
    
    TKO_USDT("TKOUSDT", "0.001", "0.1"),
    
    PUNDIX_ETH("PUNDIXETH", "0.0000001", "0.1"),
    
    PUNDIX_USDT("PUNDIXUSDT", "0.001", "0.1"),
    
    BTT_BRL("BTTBRL", "0.00001", "0.1"),
    
    BTT_EUR("BTTEUR", "0.000001", "1"),
    
    HOT_EUR("HOTEUR", "0.000001", "0.1"),
    
    WIN_EUR("WINEUR", "0.0000001", "1"),
    
    TLM_BTC("TLMBTC", "0.00000001", "1"),
    
    TLM_BUSD("TLMBUSD", "0.0001", "1"),
    
    TLM_USDT("TLMUSDT", "0.0001", "1"),
    
    BTG_BUSD("BTGBUSD", "0.01", "0.01"),
    
    BTG_USDT("BTGUSDT", "0.01", "0.01"),
    
    HOT_BUSD("HOTBUSD", "0.000001", "0.1"),
    
    BNB_UAH("BNBUAH", "1", "0.001"),
    
    ONT_TRY("ONTTRY", "0.001", "1"),
    
    VET_EUR("VETEUR", "0.00001", "0.01"),
    
    VET_GBP("VETGBP", "0.00001", "0.01"),
    
    WIN_BRL("WINBRL", "0.000001", "1"),
    
    MIR_BTC("MIRBTC", "0.00000001", "0.1"),
    
    MIR_BUSD("MIRBUSD", "0.001", "0.1"),
    
    MIR_USDT("MIRUSDT", "0.001", "0.1"),
    
    BAR_BTC("BARBTC", "0.0000001", "0.01"),
    
    BAR_BUSD("BARBUSD", "0.01", "0.01"),
    
    BAR_USDT("BARUSDT", "0.01", "0.01"),
    
    FORTH_BTC("FORTHBTC", "0.0000001", "0.01"),
    
    FORTH_BUSD("FORTHBUSD", "0.01", "0.01"),
    
    FORTH_USDT("FORTHUSDT", "0.01", "0.01"),
    
    CAKE_GBP("CAKEGBP", "0.01", "0.01"),
    
    DOGE_RUB("DOGERUB", "0.01", "1"),
    
    WRX_EUR("WRXEUR", "0.0001", "0.1"),
    
    EZ_BTC("EZBTC", "0.00000001", "0.1"),
    
    EZ_ETH("EZETH", "0.000001", "0.1"),
    
    BAKE_USDT("BAKEUSDT", "0.001", "0.1"),
    
    BURGER_BUSD("BURGERBUSD", "0.001", "0.1"),
    
    BURGER_USDT("BURGERUSDT", "0.001", "0.1"),
    
    SLP_BUSD("SLPBUSD", "0.0001", "1"),
    
    SLP_USDT("SLPUSDT", "0.0001", "1"),
    
    TRX_EUR("TRXEUR", "0.00001", "1"),
    
    VET_TRY("VETTRY", "0.0001", "0.1"),
    
    SHIB_USDT("SHIBUSDT", "0.00000001", "1"),
    
    SHIB_BUSD("SHIBBUSD", "0.00000001", "1"),
    
    ICP_BTC("ICPBTC", "0.000001", "0.01"),
    
    ICP_BNB("ICPBNB", "0.0001", "0.01"),
    
    ICP_BUSD("ICPBUSD", "0.01", "0.01"),
    
    ICP_USDT("ICPUSDT", "0.01", "0.01"),
    
    SHIB_EUR("SHIBEUR", "0.00000001", "1"),
    
    ETC_EUR("ETCEUR", "0.01", "0.01"),
    
    DOGE_BIDR("DOGEBIDR", "1", "1"),
    
    AR_BTC("ARBTC", "0.0000001", "0.01"),
    
    AR_BNB("ARBNB", "0.00001", "0.01"),
    
    AR_BUSD("ARBUSD", "0.01", "0.01"),
    
    AR_USDT("ARUSDT", "0.01", "0.01"),
    
    POLS_BTC("POLSBTC", "0.00000001", "0.1"),
    
    POLS_BNB("POLSBNB", "0.000001", "0.1"),
    
    POLS_BUSD("POLSBUSD", "0.001", "0.1"),
    
    POLS_USDT("POLSUSDT", "0.001", "0.1"),
    
    MDX_BTC("MDXBTC", "0.00000001", "0.1"),
    
    MDX_BNB("MDXBNB", "0.000001", "0.1"),
    
    MDX_BUSD("MDXBUSD", "0.001", "0.1"),
    
    MDX_USDT("MDXUSDT", "0.001", "0.1"),
    
    MASK_BNB("MASKBNB", "0.00001", "0.1"),
    
    MASK_BUSD("MASKBUSD", "0.001", "0.1"),
    
    MASK_USDT("MASKUSDT", "0.001", "0.1"),
    
    LPT_BTC("LPTBTC", "0.0000001", "0.01"),
    
    LPT_BNB("LPTBNB", "0.00001", "0.01"),
    
    LPT_BUSD("LPTBUSD", "0.01", "0.01"),
    
    LPT_USDT("LPTUSDT", "0.01", "0.01"),
    
    ETH_UAH("ETHUAH", "1", "0.0001"),
    
    MATIC_BRL("MATICBRL", "0.001", "0.1"),
    
    SOL_EUR("SOLEUR", "0.01", "0.01"),
    
    SHIB_BRL("SHIBBRL", "0.00000001", "1"),
    
    AGIX_BTC("AGIXBTC", "0.00000001", "1"),
    
    ICP_EUR("ICPEUR", "0.01", "0.01"),
    
    MATIC_GBP("MATICGBP", "0.0001", "0.1"),
    
    SHIB_TRY("SHIBTRY", "0.00000001", "1"),
    
    MATIC_BIDR("MATICBIDR", "1", "0.1"),
    
    MATIC_RUB("MATICRUB", "0.01", "0.1"),
    
    NU_BTC("NUBTC", "0.00000001", "1"),
    
    NU_BNB("NUBNB", "0.0000001", "1"),
    
    NU_BUSD("NUBUSD", "0.0001", "1"),
    
    NU_USDT("NUUSDT", "0.0001", "1"),
    
    XVG_USDT("XVGUSDT", "0.00001", "1"),
    
    RLC_BUSD("RLCBUSD", "0.001", "0.1"),
    
    CELR_BUSD("CELRBUSD", "0.00001", "1"),
    
    ATM_BUSD("ATMBUSD", "0.01", "0.01"),
    
    ZEN_BUSD("ZENBUSD", "0.01", "0.01"),
    
    FTM_BUSD("FTMBUSD", "0.0001", "1"),
    
    THETA_BUSD("THETABUSD", "0.001", "0.1"),
    
    WIN_BUSD("WINBUSD", "0.0000001", "1"),
    
    KAVA_BUSD("KAVABUSD", "0.001", "0.1"),
    
    XEM_BUSD("XEMBUSD", "0.0001", "1"),
    
    ATA_BTC("ATABTC", "0.00000001", "1"),
    
    ATA_BNB("ATABNB", "0.000001", "1"),
    
    ATA_BUSD("ATABUSD", "0.0001", "1"),
    
    ATA_USDT("ATAUSDT", "0.0001", "1"),
    
    GTC_BTC("GTCBTC", "0.0000001", "0.1"),
    
    GTC_BUSD("GTCBUSD", "0.001", "0.1"),
    
    GTC_USDT("GTCUSDT", "0.001", "0.1"),
    
    TORN_BTC("TORNBTC", "0.0000001", "0.01"),
    
    TORN_BNB("TORNBNB", "0.00001", "0.01"),
    
    TORN_BUSD("TORNBUSD", "0.01", "0.01"),
    
    TORN_USDT("TORNUSDT", "0.01", "0.01"),
    
    MATIC_TRY("MATICTRY", "0.001", "0.1"),
    
    SOL_GBP("SOLGBP", "0.01", "0.01"),
    
    BAKE_BTC("BAKEBTC", "0.00000001", "0.1"),
    
    COTI_BUSD("COTIBUSD", "0.0001", "1"),
    
    KEEP_BTC("KEEPBTC", "0.00000001", "1"),
    
    KEEP_BNB("KEEPBNB", "0.0000001", "1"),
    
    KEEP_BUSD("KEEPBUSD", "0.0001", "1"),
    
    KEEP_USDT("KEEPUSDT", "0.0001", "1"),
    
    SOL_TRY("SOLTRY", "0.1", "0.01"),
    
    RUNE_GBP("RUNEGBP", "0.001", "0.1"),
    
    SOL_BRL("SOLBRL", "0.1", "0.01"),
    
    SC_BUSD("SCBUSD", "0.00001", "1"),
    
    CHR_BUSD("CHRBUSD", "0.0001", "1"),
    
    STMX_BUSD("STMXBUSD", "0.00001", "1"),
    
    HNT_BUSD("HNTBUSD", "0.01", "0.01"),
    
    FTT_BUSD("FTTBUSD", "0.01", "0.01"),
    
    DOCK_BUSD("DOCKBUSD", "0.00001", "1"),
    
    ADA_BIDR("ADABIDR", "1", "0.1"),
    
    ERN_BNB("ERNBNB", "0.00001", "0.1"),
    
    ERN_BUSD("ERNBUSD", "0.001", "0.1"),
    
    ERN_USDT("ERNUSDT", "0.001", "0.1"),
    
    KLAY_BTC("KLAYBTC", "0.00000001", "0.1"),
    
    KLAY_BNB("KLAYBNB", "0.000001", "1"),
    
    KLAY_BUSD("KLAYBUSD", "0.001", "0.1"),
    
    KLAY_USDT("KLAYUSDT", "0.001", "0.1"),
    
    RUNE_EUR("RUNEEUR", "0.001", "0.1"),
    
    MATIC_AUD("MATICAUD", "0.001", "0.1"),
    
    DOT_RUB("DOTRUB", "1", "0.01"),
    
    UTK_BUSD("UTKBUSD", "0.0001", "1"),
    
    IOTX_BUSD("IOTXBUSD", "0.00001", "1"),
    
    PHA_USDT("PHAUSDT", "0.0001", "1"),
    
    SOL_RUB("SOLRUB", "1", "0.01"),
    
    BUSD_UAH("BUSDUAH", "0.01", "1"),
    
    BOND_BTC("BONDBTC", "0.0000001", "0.01"),
    
    BOND_BUSD("BONDBUSD", "0.01", "0.01"),
    
    BOND_USDT("BONDUSDT", "0.01", "0.01"),
    
    MLN_BTC("MLNBTC", "0.000001", "0.001"),
    
    MLN_BNB("MLNBNB", "0.0001", "0.001"),
    
    MLN_BUSD("MLNBUSD", "0.1", "0.001"),
    
    MLN_USDT("MLNUSDT", "0.1", "0.001"),
    
    GRT_TRY("GRTTRY", "0.001", "1"),
    
    DOT_AUD("DOTAUD", "0.01", "0.01"),
    
    DEXE_USDT("DEXEUSDT", "0.01", "0.01"),
    
    LTO_BUSD("LTOBUSD", "0.0001", "1"),
    
    ADX_BUSD("ADXBUSD", "0.0001", "1"),
    
    QUICK_BTC("QUICKBTC", "0.000001", "0.001"),
    
    QUICK_BNB("QUICKBNB", "0.001", "0.001"),
    
    QUICK_BUSD("QUICKBUSD", "0.1", "0.001"),
    
    C98_USDT("C98USDT", "0.001", "0.1"),
    
    C98_BUSD("C98BUSD", "0.001", "0.1"),
    
    C98_BNB("C98BNB", "0.000001", "0.1"),
    
    C98_BTC("C98BTC", "0.00000001", "0.1"),
    
    CLV_BTC("CLVBTC", "0.00000001", "0.1"),
    
    CLV_BNB("CLVBNB", "0.000001", "0.1"),
    
    CLV_BUSD("CLVBUSD", "0.001", "0.1"),
    
    CLV_USDT("CLVUSDT", "0.001", "0.1"),
    
    QNT_BTC("QNTBTC", "0.000001", "0.001"),
    
    QNT_BNB("QNTBNB", "0.0001", "0.001"),
    
    QNT_BUSD("QNTBUSD", "0.1", "0.001"),
    
    QNT_USDT("QNTUSDT", "0.1", "0.001"),
    
    FLOW_BTC("FLOWBTC", "0.0000001", "0.01"),
    
    FLOW_BNB("FLOWBNB", "0.00001", "0.01"),
    
    FLOW_BUSD("FLOWBUSD", "0.01", "0.01"),
    
    FLOW_USDT("FLOWUSDT", "0.01", "0.01"),
    
    XEC_BUSD("XECBUSD", "0.00000001", "1"),
    
    AXS_BRL("AXSBRL", "0.1", "0.01"),
    
    AXS_AUD("AXSAUD", "0.01", "0.01"),
    
    TVK_USDT("TVKUSDT", "0.0001", "1"),
    
    MINA_BTC("MINABTC", "0.00000001", "0.1"),
    
    MINA_BNB("MINABNB", "0.000001", "0.1"),
    
    MINA_BUSD("MINABUSD", "0.001", "0.1"),
    
    MINA_USDT("MINAUSDT", "0.001", "0.1"),
    
    RAY_BNB("RAYBNB", "0.00001", "0.1"),
    
    RAY_BUSD("RAYBUSD", "0.001", "0.1"),
    
    RAY_USDT("RAYUSDT", "0.001", "0.1"),
    
    FARM_BTC("FARMBTC", "0.000001", "0.001"),
    
    FARM_BNB("FARMBNB", "0.0001", "0.001"),
    
    FARM_BUSD("FARMBUSD", "0.1", "0.001"),
    
    FARM_USDT("FARMUSDT", "0.1", "0.001"),
    
    ALPACA_BTC("ALPACABTC", "0.00000001", "0.1"),
    
    ALPACA_BNB("ALPACABNB", "0.000001", "0.1"),
    
    ALPACA_BUSD("ALPACABUSD", "0.0001", "0.1"),
    
    ALPACA_USDT("ALPACAUSDT", "0.0001", "0.1"),
    
    TLM_TRY("TLMTRY", "0.001", "1"),
    
    QUICK_USDT("QUICKUSDT", "0.1", "0.001"),
    
    ORN_BUSD("ORNBUSD", "0.001", "0.1"),
    
    MBOX_BTC("MBOXBTC", "0.00000001", "0.1"),
    
    MBOX_BNB("MBOXBNB", "0.000001", "0.1"),
    
    MBOX_BUSD("MBOXBUSD", "0.001", "0.1"),
    
    MBOX_USDT("MBOXUSDT", "0.001", "0.1"),
    
    VGX_BTC("VGXBTC", "0.00000001", "0.1"),
    
    VGX_ETH("VGXETH", "0.000001", "0.1"),
    
    FOR_USDT("FORUSDT", "0.00001", "1"),
    
    REQ_USDT("REQUSDT", "0.0001", "1"),
    
    GHST_USDT("GHSTUSDT", "0.001", "0.1"),
    
    TRU_RUB("TRURUB", "0.01", "1"),
    
    FIS_BRL("FISBRL", "0.01", "0.1"),
    
    WAXP_USDT("WAXPUSDT", "0.0001", "1"),
    
    WAXP_BUSD("WAXPBUSD", "0.0001", "1"),
    
    WAXP_BNB("WAXPBNB", "0.0000001", "1"),
    
    WAXP_BTC("WAXPBTC", "0.00000001", "1"),
    
    TRIBE_BTC("TRIBEBTC", "0.00000001", "1"),
    
    TRIBE_BNB("TRIBEBNB", "0.000001", "1"),
    
    TRIBE_BUSD("TRIBEBUSD", "0.0001", "1"),
    
    TRIBE_USDT("TRIBEUSDT", "0.0001", "1"),
    
    GNO_USDT("GNOUSDT", "0.1", "0.001"),
    
    GNO_BUSD("GNOBUSD", "0.1", "0.001"),
    
    GNO_BNB("GNOBNB", "0.0001", "0.001"),
    
    GNO_BTC("GNOBTC", "0.000001", "0.001"),
    
    ARPA_TRY("ARPATRY", "0.0001", "1"),
    
    PROM_BTC("PROMBTC", "0.0000001", "0.01"),
    
    MTL_BUSD("MTLBUSD", "0.001", "0.1"),
    
    OGN_BUSD("OGNBUSD", "0.0001", "0.1"),
    
    XEC_USDT("XECUSDT", "0.00000001", "1"),
    
    C98_BRL("C98BRL", "0.01", "0.1"),
    
    SOL_AUD("SOLAUD", "0.01", "0.001"),
    
    SUSHI_BIDR("SUSHIBIDR", "1", "0.01"),
    
    XRP_BIDR("XRPBIDR", "1", "0.1"),
    
    POLY_BUSD("POLYBUSD", "0.0001", "0.1"),
    
    ELF_USDT("ELFUSDT", "0.0001", "0.1"),
    
    DYDX_USDT("DYDXUSDT", "0.001", "0.01"),
    
    DYDX_BUSD("DYDXBUSD", "0.001", "0.01"),
    
    DYDX_BNB("DYDXBNB", "0.000001", "0.01"),
    
    DYDX_BTC("DYDXBTC", "0.00000001", "0.01"),
    
    ELF_BUSD("ELFBUSD", "0.0001", "0.1"),
    
    POLY_USDT("POLYUSDT", "0.0001", "0.1"),
    
    IDEX_USDT("IDEXUSDT", "0.00001", "0.1"),
    
    VIDT_USDT("VIDTUSDT", "0.0001", "0.1"),
    
    SOL_BIDR("SOLBIDR", "1", "0.0001"),
    
    AXS_BIDR("AXSBIDR", "1", "0.001"),
    
    BTC_USDP("BTCUSDP", "0.01", "0.00001"),
    
    ETH_USDP("ETHUSDP", "0.01", "0.0001"),
    
    BNB_USDP("BNBUSDP", "0.01", "0.001"),
    
    USDP_BUSD("USDPBUSD", "0.0001", "0.01"),
    
    USDP_USDT("USDPUSDT", "0.0001", "0.01"),
    
    GALA_USDT("GALAUSDT", "0.00001", "1"),
    
    GALA_BUSD("GALABUSD", "0.00001", "1"),
    
    GALA_BNB("GALABNB", "0.00000001", "1"),
    
    GALA_BTC("GALABTC", "0.00000001", "1"),
    
    FTM_BIDR("FTMBIDR", "1", "0.1"),
    
    KSM_AUD("KSMAUD", "0.1", "0.001"),
    
    SUN_BUSD("SUNBUSD", "0.00001", "1"),
    
    ILV_USDT("ILVUSDT", "0.1", "0.001"),
    
    ILV_BUSD("ILVBUSD", "0.1", "0.001"),
    
    ILV_BNB("ILVBNB", "0.001", "0.001"),
    
    ILV_BTC("ILVBTC", "0.00001", "0.001"),
    
    REN_BUSD("RENBUSD", "0.0001", "1"),
    
    YGG_USDT("YGGUSDT", "0.001", "0.1"),
    
    YGG_BUSD("YGGBUSD", "0.001", "0.1"),
    
    YGG_BNB("YGGBNB", "0.00001", "0.1"),
    
    YGG_BTC("YGGBTC", "0.0000001", "0.1"),
    
    STX_BUSD("STXBUSD", "0.001", "0.1"),
    
    SYS_USDT("SYSUSDT", "0.0001", "1"),
    
    DF_USDT("DFUSDT", "0.0001", "1"),
    
    SOL_USDC("SOLUSDC", "0.01", "0.01"),
    
    ARPA_RUB("ARPARUB", "0.001", "1"),
    
    LTC_UAH("LTCUAH", "1", "0.001"),
    
    FET_BUSD("FETBUSD", "0.0001", "1"),
    
    ARPA_BUSD("ARPABUSD", "0.00001", "0.1"),
    
    LSK_BUSD("LSKBUSD", "0.001", "0.1"),
    
    AVAX_BIDR("AVAXBIDR", "1", "0.01"),
    
    ALICE_BIDR("ALICEBIDR", "1", "0.01"),
    
    FIDA_USDT("FIDAUSDT", "0.001", "0.1"),
    
    FIDA_BUSD("FIDABUSD", "0.001", "0.1"),
    
    FIDA_BNB("FIDABNB", "0.00001", "0.1"),
    
    FIDA_BTC("FIDABTC", "0.0000001", "0.1"),
    
    DENT_BUSD("DENTBUSD", "0.000001", "1"),
    
    FRONT_USDT("FRONTUSDT", "0.0001", "1"),
    
    CVP_USDT("CVPUSDT", "0.001", "0.1"),
    
    AGLD_BTC("AGLDBTC", "0.00000001", "0.1"),
    
    AGLD_BNB("AGLDBNB", "0.000001", "0.1"),
    
    AGLD_BUSD("AGLDBUSD", "0.001", "0.1"),
    
    AGLD_USDT("AGLDUSDT", "0.001", "0.1"),
    
    RAD_BTC("RADBTC", "0.0000001", "0.1"),
    
    RAD_BNB("RADBNB", "0.00001", "0.1"),
    
    RAD_BUSD("RADBUSD", "0.001", "0.1"),
    
    RAD_USDT("RADUSDT", "0.001", "0.1"),
    
    HIVE_BUSD("HIVEBUSD", "0.0001", "1"),
    
    STPT_BUSD("STPTBUSD", "0.00001", "0.1"),
    
    BETA_BTC("BETABTC", "0.00000001", "1"),
    
    BETA_BNB("BETABNB", "0.0000001", "1"),
    
    BETA_BUSD("BETABUSD", "0.00001", "1"),
    
    BETA_USDT("BETAUSDT", "0.00001", "1"),
    
    SHIB_AUD("SHIBAUD", "0.00000001", "1"),
    
    RARE_BTC("RAREBTC", "0.00000001", "0.1"),
    
    RARE_BNB("RAREBNB", "0.000001", "0.1"),
    
    RARE_BUSD("RAREBUSD", "0.001", "0.1"),
    
    RARE_USDT("RAREUSDT", "0.001", "0.1"),
    
    AVAX_BRL("AVAXBRL", "0.1", "0.01"),
    
    AVAX_AUD("AVAXAUD", "0.01", "0.01"),
    
    LUNA_AUD("LUNAAUD", "0.01", "0.01"),
    
    TROY_BUSD("TROYBUSD", "0.000001", "1"),
    
    AXS_ETH("AXSETH", "0.00001", "0.001"),
    
    FTM_ETH("FTMETH", "0.0000001", "0.1"),
    
    SOL_ETH("SOLETH", "0.00001", "0.001"),
    
    SSV_BTC("SSVBTC", "0.0000001", "0.01"),
    
    SSV_ETH("SSVETH", "0.000001", "0.01"),
    
    LAZIO_TRY("LAZIOTRY", "0.001", "0.01"),
    
    LAZIO_EUR("LAZIOEUR", "0.0001", "0.01"),
    
    LAZIO_BTC("LAZIOBTC", "0.00000001", "0.01"),
    
    LAZIO_USDT("LAZIOUSDT", "0.0001", "0.01"),
    
    CHESS_BTC("CHESSBTC", "0.00000001", "0.1"),
    
    CHESS_BNB("CHESSBNB", "0.000001", "0.1"),
    
    CHESS_BUSD("CHESSBUSD", "0.001", "0.1"),
    
    CHESS_USDT("CHESSUSDT", "0.001", "0.1"),
    
    FTM_AUD("FTMAUD", "0.0001", "1"),
    
    FTM_BRL("FTMBRL", "0.01", "0.1"),
    
    SCRT_BUSD("SCRTBUSD", "0.001", "0.1"),
    
    ADX_USDT("ADXUSDT", "0.0001", "1"),
    
    AUCTION_USDT("AUCTIONUSDT", "0.01", "0.01"),
    
    CELO_BUSD("CELOBUSD", "0.001", "0.1"),
    
    FTM_RUB("FTMRUB", "0.1", "0.1"),
    
    NU_AUD("NUAUD", "0.0001", "1"),
    
    NU_RUB("NURUB", "0.01", "0.1"),
    
    REEF_TRY("REEFTRY", "0.0001", "1"),
    
    REEF_BIDR("REEFBIDR", "0.1", "1"),
    
    SHIB_DOGE("SHIBDOGE", "0.0000001", "1"),
    
    DAR_USDT("DARUSDT", "0.00001", "1"),
    
    DAR_BUSD("DARBUSD", "0.00001", "1"),
    
    DAR_BNB("DARBNB", "0.00000001", "1"),
    
    DAR_BTC("DARBTC", "0.00000001", "1"),
    
    BNX_BTC("BNXBTC", "0.000001", "0.001"),
    
    BNX_BNB("BNXBNB", "0.0001", "0.001"),
    
    BNX_BUSD("BNXBUSD", "0.1", "0.001"),
    
    BNX_USDT("BNXUSDT", "0.1", "0.001"),
    
    RGT_USDT("RGTUSDT", "0.01", "0.01"),
    
    RGT_BTC("RGTBTC", "0.0000001", "0.01"),
    
    RGT_BUSD("RGTBUSD", "0.01", "0.01"),
    
    RGT_BNB("RGTBNB", "0.00001", "0.01"),
    
    LAZIO_BUSD("LAZIOBUSD", "0.0001", "0.01"),
    
    OXT_BUSD("OXTBUSD", "0.0001", "1"),
    
    MANA_TRY("MANATRY", "0.01", "0.1"),
    
    ALGO_RUB("ALGORUB", "0.1", "0.1"),
    
    SHIB_UAH("SHIBUAH", "0.000001", "1"),
    
    LUNA_BIDR("LUNABIDR", "1", "0.01"),
    
    AUD_USDC("AUDUSDC", "0.0001", "1"),
    
    MOVR_BTC("MOVRBTC", "0.000001", "0.001"),
    
    MOVR_BNB("MOVRBNB", "0.0001", "0.001"),
    
    MOVR_BUSD("MOVRBUSD", "0.1", "0.001"),
    
    MOVR_USDT("MOVRUSDT", "0.1", "0.001"),
    
    CITY_BTC("CITYBTC", "0.0000001", "0.01"),
    
    CITY_BNB("CITYBNB", "0.00001", "0.01"),
    
    CITY_BUSD("CITYBUSD", "0.01", "0.01"),
    
    CITY_USDT("CITYUSDT", "0.01", "0.01"),
    
    ENS_BTC("ENSBTC", "0.0000001", "0.01"),
    
    ENS_BNB("ENSBNB", "0.00001", "0.01"),
    
    ENS_BUSD("ENSBUSD", "0.01", "0.01"),
    
    ENS_USDT("ENSUSDT", "0.01", "0.01"),
    
    SAND_ETH("SANDETH", "0.0000001", "0.1"),
    
    DOT_ETH("DOTETH", "0.000001", "0.01"),
    
    MATIC_ETH("MATICETH", "0.0000001", "0.1"),
    
    ANKR_BUSD("ANKRBUSD", "0.00001", "0.1"),
    
    SAND_TRY("SANDTRY", "0.01", "0.1"),
    
    MANA_BRL("MANABRL", "0.01", "0.1"),
    
    KP3R_USDT("KP3RUSDT", "0.01", "0.01"),
    
    QI_USDT("QIUSDT", "0.0001", "1"),
    
    QI_BUSD("QIBUSD", "0.0001", "1"),
    
    QI_BNB("QIBNB", "0.0000001", "1"),
    
    QI_BTC("QIBTC", "0.00000001", "1"),
    
    PORTO_BTC("PORTOBTC", "0.00000001", "0.01"),
    
    PORTO_USDT("PORTOUSDT", "0.0001", "0.01"),
    
    PORTO_TRY("PORTOTRY", "0.01", "0.01"),
    
    PORTO_EUR("PORTOEUR", "0.0001", "0.01"),
    
    POWR_USDT("POWRUSDT", "0.0001", "1"),
    
    POWR_BUSD("POWRBUSD", "0.0001", "1"),
    
    AVAX_ETH("AVAXETH", "0.00001", "0.01"),
    
    SLP_TRY("SLPTRY", "0.0001", "1"),
    
    FIS_TRY("FISTRY", "0.01", "0.1"),
    
    LRC_TRY("LRCTRY", "0.01", "0.1"),
    
    CHR_ETH("CHRETH", "0.0000001", "0.1"),
    
    FIS_BIDR("FISBIDR", "1", "0.1"),
    
    VGX_USDT("VGXUSDT", "0.001", "0.1"),
    
    GALA_ETH("GALAETH", "0.00000001", "1"),
    
    JASMY_USDT("JASMYUSDT", "0.0001", "0.1"),
    
    JASMY_BUSD("JASMYBUSD", "0.0001", "0.1"),
    
    JASMY_BNB("JASMYBNB", "0.0000001", "0.1"),
    
    JASMY_BTC("JASMYBTC", "0.00000001", "0.1"),
    
    AMP_BTC("AMPBTC", "0.00000001", "1"),
    
    AMP_BNB("AMPBNB", "0.00000001", "1"),
    
    AMP_BUSD("AMPBUSD", "0.00001", "1"),
    
    AMP_USDT("AMPUSDT", "0.00001", "1"),
    
    PLA_BTC("PLABTC", "0.00000001", "0.01"),
    
    PLA_BNB("PLABNB", "0.000001", "0.01"),
    
    PLA_BUSD("PLABUSD", "0.001", "0.01"),
    
    PLA_USDT("PLAUSDT", "0.001", "0.01"),
    
    PYR_BTC("PYRBTC", "0.0000001", "0.001"),
    
    PYR_BUSD("PYRBUSD", "0.01", "0.001"),
    
    PYR_USDT("PYRUSDT", "0.01", "0.001"),
    
    RNDR_BTC("RNDRBTC", "0.0000001", "0.01"),
    
    RNDR_USDT("RNDRUSDT", "0.001", "0.01"),
    
    RNDR_BUSD("RNDRBUSD", "0.001", "0.01"),
    
    ALCX_BTC("ALCXBTC", "0.000001", "0.0001"),
    
    ALCX_BUSD("ALCXBUSD", "0.1", "0.0001"),
    
    ALCX_USDT("ALCXUSDT", "0.1", "0.0001"),
    
    SANTOS_BTC("SANTOSBTC", "0.00000001", "0.01"),
    
    SANTOS_USDT("SANTOSUSDT", "0.001", "0.01"),
    
    SANTOS_BRL("SANTOSBRL", "0.01", "0.01"),
    
    SANTOS_TRY("SANTOSTRY", "0.01", "0.01"),
    
    MC_BTC("MCBTC", "0.0000001", "0.01"),
    
    MC_BUSD("MCBUSD", "0.01", "0.01"),
    
    MC_USDT("MCUSDT", "0.01", "0.01"),
    
    BEL_TRY("BELTRY", "0.01", "0.01"),
    
    COCOS_BUSD("COCOSBUSD", "0.0001", "1"),
    
    DENT_TRY("DENTTRY", "0.00001", "1"),
    
    ENJ_TRY("ENJTRY", "0.01", "0.01"),
    
    NEO_RUB("NEORUB", "1", "0.001"),
    
    SAND_AUD("SANDAUD", "0.0001", "1"),
    
    SLP_BIDR("SLPBIDR", "0.1", "1"),
    
    ANY_BTC("ANYBTC", "0.0000001", "0.01"),
    
    ANY_BUSD("ANYBUSD", "0.01", "0.01"),
    
    ANY_USDT("ANYUSDT", "0.01", "0.01"),
    
    BICO_BTC("BICOBTC", "0.0000001", "0.01"),
    
    BICO_BUSD("BICOBUSD", "0.001", "0.01"),
    
    BICO_USDT("BICOUSDT", "0.001", "0.01"),
    
    FLUX_BTC("FLUXBTC", "0.00000001", "0.01"),
    
    FLUX_BUSD("FLUXBUSD", "0.001", "0.01"),
    
    FLUX_USDT("FLUXUSDT", "0.001", "0.01"),
    
    ALICE_TRY("ALICETRY", "0.1", "0.001"),
    
    FXS_USDT("FXSUSDT", "0.001", "0.1"),
    
    GALA_BRL("GALABRL", "0.001", "0.1"),
    
    GALA_TRY("GALATRY", "0.001", "0.1"),
    
    LUNA_TRY("LUNATRY", "0.1", "0.001"),
    
    REQ_BUSD("REQBUSD", "0.0001", "1"),
    
    SAND_BRL("SANDBRL", "0.01", "0.01"),
    
    MANA_BIDR("MANABIDR", "1", "0.01"),
    
    SAND_BIDR("SANDBIDR", "1", "0.01"),
    
    VOXEL_BTC("VOXELBTC", "0.00000001", "0.1"),
    
    VOXEL_BNB("VOXELBNB", "0.0000001", "0.1"),
    
    VOXEL_BUSD("VOXELBUSD", "0.0001", "0.1"),
    
    VOXEL_USDT("VOXELUSDT", "0.0001", "0.1"),
    
    COS_BUSD("COSBUSD", "0.00001", "0.1"),
    
    CTXC_BUSD("CTXCBUSD", "0.0001", "1"),
    
    FTM_TRY("FTMTRY", "0.01", "0.01"),
    
    MANA_BNB("MANABNB", "0.000001", "0.01"),
    
    MINA_TRY("MINATRY", "0.01", "0.01"),
    
    XTZ_TRY("XTZTRY", "0.01", "0.01"),
    
    HIGH_BTC("HIGHBTC", "0.0000001", "0.001"),
    
    HIGH_BUSD("HIGHBUSD", "0.01", "0.001"),
    
    HIGH_USDT("HIGHUSDT", "0.01", "0.001"),
    
    CVX_BTC("CVXBTC", "0.0000001", "0.001"),
    
    CVX_BUSD("CVXBUSD", "0.01", "0.001"),
    
    CVX_USDT("CVXUSDT", "0.01", "0.001"),
    
    PEOPLE_BTC("PEOPLEBTC", "0.00000001", "0.1"),
    
    PEOPLE_BUSD("PEOPLEBUSD", "0.0001", "0.1"),
    
    PEOPLE_USDT("PEOPLEUSDT", "0.0001", "0.1"),
    
    OOKI_BUSD("OOKIBUSD", "0.00001", "1"),
    
    OOKI_USDT("OOKIUSDT", "0.00001", "1"),
    
    COCOS_TRY("COCOSTRY", "0.01", "0.01"),
    
    GXS_BNB("GXSBNB", "0.000001", "0.01"),
    
    LINK_BNB("LINKBNB", "0.00001", "0.001"),
    
    LUNA_ETH("LUNAETH", "0.00001", "0.001"),
    
    MDT_BUSD("MDTBUSD", "0.00001", "0.1"),
    
    NULS_BUSD("NULSBUSD", "0.0001", "1"),
    
    SPELL_BTC("SPELLBTC", "0.00000001", "1"),
    
    SPELL_USDT("SPELLUSDT", "0.00001", "1"),
    
    SPELL_BUSD("SPELLBUSD", "0.00001", "1"),
    
    UST_BTC("USTBTC", "0.00000001", "0.01"),
    
    UST_BUSD("USTBUSD", "0.0001", "1"),
    
    UST_USDT("USTUSDT", "0.0001", "1"),
    
    JOE_BTC("JOEBTC", "0.00000001", "0.01"),
    
    JOE_BUSD("JOEBUSD", "0.001", "0.01"),
    
    JOE_USDT("JOEUSDT", "0.001", "0.01"),
    
    ;
    // generate code from binance api
    // generate code from binance api
    // generate code from binance api

    public final BigDecimal pricePrecision;
    public final BigDecimal quantityPrecision;
    public final String STR;

    Symbol(String str, String precision, String quantity) {
        this.STR = str;
        this.pricePrecision = new BigDecimal(precision);
        this.quantityPrecision = new BigDecimal(quantity);
    }

    public String toUpperCaseStr() {
        return this.STR;
    }

    public String toLowerCase() {
        return STR.toLowerCase();
    }

    @Override
    public String getStr() {
        return STR;
    }

    @Override
    public Symbol[] getValues() {
        return values();
    }

}
