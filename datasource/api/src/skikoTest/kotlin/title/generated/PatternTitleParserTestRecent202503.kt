// @formatter:off
@file:Suppress(
  "FunctionName",
  "ClassName",
  "RedundantVisibilityModifier",
  "PackageDirectoryMismatch",
  "NonAsciiCharacters",
  "SpellCheckingInspection",
)

import me.him188.ani.datasources.api.SubtitleKind
import me.him188.ani.datasources.api.title.PatternBasedTitleParserTestSuite
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * 原名: `Recent202503`
 * 数据源: `dmhy`
 *
 * 由 `test-codegen` 的 `GenerateTests.kt` 生成, 不要手动修改!
 * 如果你优化了解析器, 这些 test 可能会失败, 请检查是否它是因为以前解析错误而现在解析正确了. 
 * 如果是, 请更新测试数据: 执行 `GenerateTests.kt`.
 */
public class PatternTitleParserTestRecent202503 : PatternBasedTitleParserTestSuite() {
  @Test
  public fun `690641_YUI_Yami_Shibai_14_09_x264_1080p_CHT`() {
    kotlin.run {
    val r = parse("【悠哈璃羽字幕社&西農YUI漢化組】[暗芝居 第十四季_Yami Shibai 14][09][x264 1080p][CHT]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690640_YUI_Yami_Shibai_14_09_x264_1080p_CHS`() {
    kotlin.run {
    val r = parse("【悠哈璃羽字幕社&西农YUI汉化组】[暗芝居 第十四季_Yami Shibai 14][09][x264 1080p][CHS]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690639_LoliHouse_Nihon_e_Youkoso_Elf_san___08_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 精灵来日 / 歡迎來到日本，妖精小姐。 / 日本へようこそエルフさん。 / Nihon e Youkoso Elf-san. - 08 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690638_Arafou_Otoko_no_Isekai_Tsuuhan_Seikatsu_08_webRip_AVC_8bit_1080P_AAC`() {
    kotlin.run {
    val r =
        parse("[黑白字幕组]中年男的异世界网购生活 / Arafou Otoko no Isekai Tsuuhan Seikatsu [08] [webRip] [AVC-8bit 1080P AAC] [繁日内嵌]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690637_Magic_Maker_Isekai_Mahou_no_Tsukurikata_08_webRip_AVC_8bit_1080P_AAC`() {
    kotlin.run {
    val r =
        parse("[黑白字幕组]魔术发明家 ~异世界魔法的制作方法~ / Magic Maker: Isekai Mahou no Tsukurikata [08] [webRip] [AVC-8bit 1080P AAC] [繁日内嵌]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690636_Botsuraku_Yotei_no_Kizoku_dakedo_Hima_Datta_kara_Mahou_wo_Kiwametemita_07_webRip_AVC_8bit_1080P_AAC`() {
    kotlin.run {
    val r =
        parse("[黑白字幕组]虽然我是注定没落的贵族，闲来无事只好来深究魔法 / Botsuraku Yotei no Kizoku dakedo, Hima Datta kara Mahou wo Kiwametemita [07] [webRip] [AVC-8bit 1080P AAC] [繁日内嵌]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690635_jibaketa_ViuTV_Captain_Tsubasa_Season_2___Junior_Youth_Hen___22_WEB_1920x1080_AVC_AACx2_SRT_ViuTV_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][ViuTV粵語]隊長小翼第二季 青少年篇 / 世少篇 / Captain Tsubasa Season 2 - Junior Youth Hen - 22 (WEB 1920x1080 AVC AACx2 SRT ViuTV CHT)")
    assertEquals("22..22", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690634_Class_no_Daikirai_na_Joshi_to_Kekkon_Suru_Koto_ni_Natta_09_1080p`() {
    kotlin.run {
    val r =
        parse("[桜都字幕组] 我和班上最讨厌的女生结婚了。 / Class no Daikirai na Joshi to Kekkon Suru Koto ni Natta. [09][1080p][简繁内封]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690633_Class_no_Daikirai_na_Joshi_to_Kekkon_Suru_Koto_ni_Natta_09_1080p`() {
    kotlin.run {
    val r =
        parse("[桜都字幕組] 我和班上最討厭的女生結婚了。 / Class no Daikirai na Joshi to Kekkon Suru Koto ni Natta. [09][1080p][繁體內嵌]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690632_Botsuraku_Yotei_no_Kizoku_dakedo_Hima_Datta_kara_Mahou_wo_Kiwametemita_07_webRip_AVC_8bit_1080P_AAC`() {
    kotlin.run {
    val r =
        parse("[黑白字幕组]虽然我是注定没落的贵族，闲来无事只好来深究魔法 / Botsuraku Yotei no Kizoku dakedo, Hima Datta kara Mahou wo Kiwametemita [07] [webRip] [AVC-8bit 1080P AAC] [简日内嵌]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690631_Magic_Maker_Isekai_Mahou_no_Tsukurikata_08_webRip_AVC_8bit_1080P_AAC`() {
    kotlin.run {
    val r =
        parse("[黑白字幕组]魔术发明家 ~异世界魔法的制作方法~ / Magic Maker: Isekai Mahou no Tsukurikata [08] [webRip] [AVC-8bit 1080P AAC] [简日内嵌]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690630_Class_no_Daikirai_na_Joshi_to_Kekkon_Suru_Koto_ni_Natta_09_1080p`() {
    kotlin.run {
    val r =
        parse("[桜都字幕组] 我和班上最讨厌的女生结婚了。 / Class no Daikirai na Joshi to Kekkon Suru Koto ni Natta. [09][1080p][简体内嵌]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690629_Arafou_Otoko_no_Isekai_Tsuuhan_Seikatsu_08_webRip_AVC_8bit_1080P_AAC`() {
    kotlin.run {
    val r =
        parse("[黑白字幕组]中年男的异世界网购生活 / Arafou Otoko no Isekai Tsuuhan Seikatsu [08] [webRip] [AVC-8bit 1080P AAC] [简日内嵌]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690628_Dr_STONE_Science_Future_04_1080p_2025_1`() {
    kotlin.run {
    val r = parse("[猎户压制部] 新石纪 第四季 科学与未来 / Dr.STONE：Science Future [04] [1080p] [繁日内嵌] [2025年1月番]")
    assertEquals("04..04", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690627_Dr_STONE_Science_Future_04_1080p_2025_1`() {
    kotlin.run {
    val r = parse("[猎户压制部] 石纪元 第四季 科学与未来 / Dr.STONE：Science Future [04] [1080p] [简日内嵌] [2025年1月番]")
    assertEquals("04..04", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690626_ANi_Promise_of_Wizard___09_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] Promise of Wizard / 魔法使的約定 - 09 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690625_ANi___10_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] 平凡上班族到異世界當上了四天王的故事 - 10 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("10..10", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690624_YYQ_New_Doraemon_A_852_2025_03_01_AVC_1080P_GB_JP`() {
    kotlin.run {
    val r = parse("[夜莺家族&YYQ字幕组]New Doraemon 哆啦A梦新番[852][2025.03.01][AVC][1080P][GB_JP]")
    assertEquals("852..852", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690623_The_Colors_Within_2024_1080p_BluRay_x264_DTS_CTRLHD`() {
    kotlin.run {
    val r = parse("你的颜色[简繁字幕].The.Colors.Within.2024.1080p.BluRay.x264.DTS-CTRLHD")
    assertEquals("null", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690622_ANi_I_Have_a_Crush_at_Work___09_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[ANi] I Have a Crush at Work / 這公司有我喜歡的人 - 09 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690620_Yoru_wa_Neko_to_Issho_3_73_HEVC_10bit_1080P`() {
    kotlin.run {
    val r = parse("[织梦字幕组] 与猫共度的夜晚 Yoru wa Neko to Issho 3 [73] [HEVC-10bit 1080P] [简日双语] [特效字幕]")
    assertEquals("73..73", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690619_SAKAMOTO_DAYS_08_WebRip_HEVC_AAC`() {
    kotlin.run {
    val r = parse("[北宇治字幕组] 坂本日常 / SAKAMOTO DAYS [08][WebRip][HEVC_AAC][简繁日內封]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690618_SAKAMOTO_DAYS_08_WebRip_HEVC_AAC`() {
    kotlin.run {
    val r = parse("[北宇治字幕组] 坂本日常 / SAKAMOTO DAYS [08][WebRip][HEVC_AAC][繁日內嵌]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690617_SAKAMOTO_DAYS_08_WebRip_HEVC_AAC`() {
    kotlin.run {
    val r = parse("[北宇治字幕组] 坂本日常 / SAKAMOTO DAYS [08][WebRip][HEVC_AAC][简日內嵌]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690616_161_WEB_MKV_2160P`() {
    kotlin.run {
    val r = parse("[吞噬星空][第161集][国语中字][WEB-MKV][2160P]")
    assertEquals("161..161", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690614_Nichijou_My_Ordinary_Life_TV_OVA_2023_05_13`() {
    kotlin.run {
    val r =
        parse("[整理搬运] 日常 (Nichijou／My Ordinary Life)：TV动画+OVA+漫画+音乐+其他；日语音轨; 外挂简中字幕 (整理时间：2023.05.13)")
    assertEquals("OVA..OVA", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public fun `690612_SweetSub_Momentary_Lily_09_WebRip_1080P_AVC_8bit`() {
    kotlin.run {
    val r = parse("[SweetSub][刹那之花][Momentary Lily][09][WebRip][1080P][AVC 8bit][简日内嵌]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690611_SweetSub_LoliHouse_Momentary_Lily___09v2_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[SweetSub&LoliHouse] 刹那之花 / Momentary Lily - 09v2 [WebRip 1080p HEVC-10bit AAC][简日内封字幕]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690610_Sentai_Red_Isekai_de_Boukensha_ni_Naru_08_1080p_WebRip_x264_AAC_CHS`() {
    kotlin.run {
    val r =
        parse("[蓝白汉化] 战队红战士在异世界当冒险者 / Sentai Red Isekai de Boukensha ni Naru [08][1080p][WebRip][x264_AAC][CHS]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690608_SweetSub_Momentary_Lily_09_WebRip_1080P_AV1_10bit`() {
    kotlin.run {
    val r = parse("[SweetSub][刹那之花][Momentary Lily][09][WebRip][1080P][AV1 10bit][简日内封]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690607_Ai_Yori_Aoshi_TV_2002_2003_OVA_CD_2024_06_30`() {
    kotlin.run {
    val r =
        parse("[整理搬运] 青出于蓝／青蓝姻缘／未婚关系 (藍より青し) (Ai Yori Aoshi)：TV动画 (2002年版、2003年版)+OVA+漫画+CD+其他；日语音轨; 外挂繁中字幕 (整理时间：2024.06.30)")
    assertEquals("OVA..OVA", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public fun `690606__21_K_ON__21_TV_2009_2010_2023_05_09`() {
    kotlin.run {
    val r =
        parse("[整理搬运] 轻音少女 (けいおん !) (K-ON !)：TV动画 (2009年版、2010年版)+剧场版+漫画+演唱会+其他；日语音轨; 外挂简中字幕 (整理时间：2023.05.09)")
    assertEquals("S?", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public fun `690605_Chibi_Maruko_chan_II_1469_2025_03_02_BIG5_1080P_MP4`() {
    kotlin.run {
    val r =
        parse("[丸子家族][櫻桃小丸子第二期(Chibi Maruko-chan II)][1469]櫻家的女兒節玩偶騷動&小玉想要午飯添菜[2025.03.02][BIG5][1080P][MP4]")
    assertEquals("1469..1469", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690604_Chibi_Maruko_chan_II_1469_2025_03_02_GB_1080P_MP4`() {
    kotlin.run {
    val r =
        parse("[丸子家族][樱桃小丸子第二期(Chibi Maruko-chan II)][1469]樱家的女儿节玩偶骚动&小玉想要午饭添菜[2025.03.02][GB][1080P][MP4]")
    assertEquals("1469..1469", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690602_ANi_Neko_ni_Tensei_Shita_Ojisan___21_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[ANi] Neko ni Tensei Shita Ojisan / 轉生成貓咪的大叔 - 21 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("21..21", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690601_Grisaia_Phantom_Trigger_09_1080p`() {
    kotlin.run {
    val r = parse("[桜都字幕组] 灰色：幻影扳机 / Grisaia：Phantom Trigger [09][1080p][简繁内封]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690600_Grisaia_Phantom_Trigger_09_1080p`() {
    kotlin.run {
    val r = parse("[桜都字幕組] 灰色：幻影扳機 / Grisaia：Phantom Trigger [09][1080p][繁體內嵌]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690599_Grisaia_Phantom_Trigger_09_1080p`() {
    kotlin.run {
    val r = parse("[桜都字幕组] 灰色：幻影扳机 / Grisaia：Phantom Trigger [09][1080p][简体内嵌]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690598_Har_Guu_TV_OVA_2023_12_12`() {
    kotlin.run {
    val r =
        parse("[整理搬运] 热带雨林的爆笑生活／哈雷小子 (ジャングルはいつもハレのちグゥ) (Haré+Guu)：TV动画+OVA+漫画+音乐+其他；日语音轨; 外挂简中字幕 (整理时间：2023.12.12)")
    assertEquals("OVA..OVA", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public fun `690596_Ai_Yori_Aoshi_TV_2002_2003_OVA_CD_2024_06_30`() {
    kotlin.run {
    val r =
        parse("【归档】[整理搬运] 青出于蓝／青蓝姻缘／未婚关系 (藍より青し) (Ai Yori Aoshi)：TV动画 (2002年版、2003年版)+OVA+漫画+CD+其他；日语音轨; 外挂繁中字幕 (整理时间：2024.06.30)")
    assertEquals("OVA..OVA", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public fun `690593_Billion_Meta_Lab_1080P_WEBRIP`() {
    kotlin.run {
    val r = parse("[Billion Meta Lab] 你的顏色 [1080P][WEBRIP][繁日內嵌雙語]")
    assertEquals("null", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690592_01_Act_2_Unnamed_Memory_Act_2_20_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[无名记忆 Act.2 / Unnamed Memory Act.2][20][1080p][简日双语][招募翻译时轴]")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690586___21_NF_WebRip_AI2160p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r = parse("[不当舔狗制作组] 青之箱 - 21 [NF WebRip AI2160p HEVC-10bit AAC][官方简繁内封字幕]")
    assertEquals("21..21", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690585___21_NF_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r = parse("[不当舔狗制作组] 青之箱 - 21 [NF WebRip 1080p HEVC-10bit AAC][官方简繁内封字幕]")
    assertEquals("21..21", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690584_LoliHouse_Douse_Koishite_Shimaunda___08_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 终究、与你相恋 / Douse, Koishite Shimaunda - 08 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690583_Dungeon_ni_Deai_o_Motomeru_no_wa_Machigatte_Iru_Darouka_Familia_Myth_V_14_1080p`() {
    kotlin.run {
    val r =
        parse("[桜都字幕组] 在地下城寻求邂逅是否搞错了什么 第五季 / Dungeon ni Deai o Motomeru no wa Machigatte Iru Darouka： Familia Myth V [14][1080p][简繁内封]")
    assertEquals("14..14", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690582_LoliHouse_Shangri_La_Frontier___46_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] Shangri-La Frontier / 香格里拉·开拓异境～粪作猎手挑战神作～ - 46 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("46..46", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690581_Dungeon_ni_Deai_o_Motomeru_no_wa_Machigatte_Iru_Darouka_Familia_Myth_V_14_1080p`() {
    kotlin.run {
    val r =
        parse("[桜都字幕組] 在地下城尋求邂逅是否搞錯了什麼 第五季 / Dungeon ni Deai o Motomeru no wa Machigatte Iru Darouka： Familia Myth V [14][1080p][繁體內嵌]")
    assertEquals("14..14", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690580_Dungeon_ni_Deai_o_Motomeru_no_wa_Machigatte_Iru_Darouka_Familia_Myth_V_14_1080p`() {
    kotlin.run {
    val r =
        parse("[桜都字幕组] 在地下城寻求邂逅是否搞错了什么 第五季 / Dungeon ni Deai o Motomeru no wa Machigatte Iru Darouka： Familia Myth V [14][1080p][简体内嵌]")
    assertEquals("14..14", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690579_LoliHouse_Medalist___09_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r = parse("[喵萌奶茶屋&LoliHouse] 金牌得主 / Medalist - 09 [WebRip 1080p HEVC-10bit AAC][简繁日内封字幕]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690578_jibaketa_TVB_Let_s_Camp_21_Yuru_Camp_Season_3___06_BD_1920x1080_x264_AACx2_SRT_TVB_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&壓制][TVB粵語]Let's Camp!露營少女/搖曳露營/Yuru Camp Season 3 - 06 [粵日雙語+內封繁體中文字幕][BD 1920x1080 x264 AACx2 SRT TVB CHT]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690577_Billion_Meta_Lab_1080P_WEBRIP`() {
    kotlin.run {
    val r = parse("[Billion Meta Lab] 你的颜色 [1080P][WEBRIP][简日内嵌双语]")
    assertEquals("null", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690576_01_Medalist_09_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[金牌得主 / Medalist][09][1080p][繁日雙語][招募翻譯]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690575_01_Medalist_09_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[金牌得主 / Medalist][09][1080p][简日双语][招募翻译]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690574_______09_CR_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r = parse("[不当舔狗制作组] 我独自升级 第二季 -起于暗影- - 09 [CR WebRip 1080p HEVC-10bit AAC][官方简繁内封字幕]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690573_Skymoon_Raws_2_Shangri_La_Frontier_2nd_Season___21_ViuTV_WEB_DL_1080p_AVC_AAC`() {
    kotlin.run {
    val r =
        parse("[Skymoon-Raws] 香格里拉·開拓異境～糞作獵手挑戰神作～第2季 / Shangri-La Frontier 2nd Season - 21 [ViuTV][WEB-DL][1080p][AVC AAC]")
    assertEquals("21..21", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690572_Skymoon_Raws_100_Hyakkano___20_ViuTV_WEB_RIP_1080p_AVC_AAC_CHT_SRT_MKV`() {
    kotlin.run {
    val r =
        parse("[Skymoon-Raws] 超超超超超喜歡你的100個女朋友 / Hyakkano - 20 [ViuTV][WEB-RIP][1080p][AVC AAC][CHT][SRT][MKV]")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690571_ANi_Season_2___19_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] 金肉人 完美超人始祖篇 Season 2 - 19 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690570_Skymoon_Raws_Kisaki_Kyouiku_kara_Nigetai_Watashi___09_ViuTV_WEB_DL_1080p_AVC_AAC`() {
    kotlin.run {
    val r =
        parse("[Skymoon-Raws] 我想蹺掉太子妃培訓 / Kisaki Kyouiku kara Nigetai Watashi - 09 [ViuTV][WEB-DL][1080p][AVC AAC]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690569_Skymoon_Raws_Mashin_Souzouden_Wataru___08_ViuTV_WEB_DL_1080p_AVC_AAC`() {
    kotlin.run {
    val r =
        parse("[Skymoon-Raws] 魔神創造傳 / Mashin Souzouden Wataru - 08 [ViuTV][WEB-DL][1080p][AVC AAC]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690567_____Rurouni_Kenshin_Meiji_Kenkaku_Romantan___Kyoto_Douran_44_1080p_2024_10`() {
    kotlin.run {
    val r =
        parse("[猎户手抄部] 神剑闯江湖 -明治剑客浪漫谭- 京都动乱 / Rurouni Kenshin：Meiji Kenkaku Romantan - Kyoto Douran [44] [1080p] [繁日内嵌] [2024年10月番]")
    assertEquals("44..44", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690566_____Rurouni_Kenshin_Meiji_Kenkaku_Romantan___Kyoto_Douran_44_1080p_2024_10`() {
    kotlin.run {
    val r =
        parse("[猎户手抄部] 浪客剑心 -明治剑客浪漫谭- 京都动乱 / Rurouni Kenshin: Meiji Kenkaku Romantan - Kyoto Douran [44] [1080p] [简日内嵌] [2024年10月番]")
    assertEquals("44..44", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690565_Dr_STONE_SCIENCE_FUTURE_03_CR_WebRip_1080p_HEVC_AAC_S4`() {
    kotlin.run {
    val r =
        parse("[芝士动物朋友] 石纪元 科学与未来 / Dr.STONE SCIENCE FUTURE [03][CR-WebRip][1080p][HEVC+AAC][简繁内封]（新石纪 第四季 S4）")
    assertEquals("03..03", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690564_LoliHouse_Kurakon___09_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 我和班上最讨厌的女生结婚了。 / クラスの大嫌いな女子と結婚することになった。 / Kurakon - 09 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690563_ANi_100___20_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] 超超超超超喜歡你的 100 個女朋友 - 20 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690562_Kuroiwa_Medaka_ni_Watashi_no_Kawaii_ga_Tsuujinai_08_webRip_AVC_8bit_1080P_AAC`() {
    kotlin.run {
    val r =
        parse("[黑白字幕组]我的可爱对黑岩目高不管用 / Kuroiwa Medaka ni Watashi no Kawaii ga Tsuujinai [08] [webRip] [AVC-8bit 1080P AAC] [繁日内嵌]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690561_Kuroiwa_Medaka_ni_Watashi_no_Kawaii_ga_Tsuujinai_08_webRip_AVC_8bit_1080P_AAC`() {
    kotlin.run {
    val r =
        parse("[黑白字幕组]我的可爱对黑岩目高不管用 / Kuroiwa Medaka ni Watashi no Kawaii ga Tsuujinai [08] [webRip] [AVC-8bit 1080P AAC] [简日内嵌]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690559_10_Touhai_Ura_Rate_Mahjong_Touhai_Roku_20_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★10月新番★[凍牌 / Touhai: Ura Rate Mahjong Touhai Roku][20][1080p][繁體][招募翻譯]")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690558_10_Touhai_Ura_Rate_Mahjong_Touhai_Roku_20_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★10月新番★[冻牌 / Touhai: Ura Rate Mahjong Touhai Roku][20][1080p][简体][招募翻译]")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690557_ANi___09_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] 我想蹺掉太子妃培訓 - 09 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690556_78_WEB_MKV_2160P`() {
    kotlin.run {
    val r = parse("[仙逆][第78集][国语中字][WEB-MKV][2160P]")
    assertEquals("78..78", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690551_10_DAIMA_Dragon_Ball_DAIMA_19_BIG5_MP4_1920X1080`() {
    kotlin.run {
    val r = parse("【幻櫻字幕組】【10月新番】【龍珠DAIMA Dragon Ball DAIMA】【19】【BIG5_MP4】【1920X1080】")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690549_10_DAIMA_Dragon_Ball_DAIMA_19_GB_MP4_1920X1080`() {
    kotlin.run {
    val r = parse("【幻樱字幕组】【10月新番】【龙珠DAIMA Dragon Ball DAIMA】【19】【GB_MP4】【1920X1080】")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690548_10_DAIMA_Dragon_Ball_DAIMA_19_BIG5_MP4_1280X720`() {
    kotlin.run {
    val r = parse("【幻櫻字幕組】【10月新番】【龍珠DAIMA Dragon Ball DAIMA】【19】【BIG5_MP4】【1280X720】")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("720P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690547_10_DAIMA_Dragon_Ball_DAIMA_19_GB_MP4_1280X720`() {
    kotlin.run {
    val r = parse("【幻樱字幕组】【10月新番】【龙珠DAIMA Dragon Ball DAIMA】【19】【GB_MP4】【1280X720】")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("720P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690546_ANi_Mashin_Creator_Wataru___08_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[ANi] Mashin Creator Wataru / 魔神創造傳 - 08 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690545_Sub_7_Tokidoki_Bosotto_Russia_go_de_Dereru_Tonari_no_Alya_san_09_HEVC_10Bit_1080P`() {
    kotlin.run {
    val r =
        parse("[新Sub][7月新番][不时轻声地以俄语遮羞的邻座艾莉同学_Tokidoki Bosotto Russia-go de Dereru Tonari no Alya-san][09][HEVC][10Bit][1080P][简日双语][招募翻译]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690544_Himitsu_no_Aipri_47`() {
    kotlin.run {
    val r = parse("[雪飄工作室][ひみつのアイプリ/Himitsu_no_Aipri/秘密的美妙公主][47][简繁外挂](检索:/美妙旋律/美妙天堂/美妙頻道/美妙魔法)")
    assertEquals("47..47", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public fun `690543_Himitsu_no_Aipri_720P_47`() {
    kotlin.run {
    val r =
        parse("[雪飄工作室][ひみつのアイプリ/Himitsu_no_Aipri/秘密的美妙公主][720P][47][繁](检索:/美妙旋律/美妙天堂/美妙頻道/美妙魔法)")
    assertEquals("47..47", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("720P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690542_LoliHouse_A_Aparida___08_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 离开A级队伍的我，和从前的弟子往迷宫深处迈进 / Aparida - 08 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690541_LoliHouse_NEET_Kunoichi___09_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 我与尼特女忍者的莫名同居生活 / NEET Kunoichi - 09 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690540_ANi_ToiletBound_Hanakokun_S02___08_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[ANi] ToiletBound Hanakokun S02 / 地縛少年花子君 第二季 - 08 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690539_ANi___21_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] 香格里拉・開拓異境～糞作獵手挑戰神作～ 第二季 - 21 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("21..21", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690538_Strange_Raw_SAKAMOTO_DAYS_S01_08_Bilibili_WEB_DL_1080P_AVC_8Bits_AAC_2_0`() {
    kotlin.run {
    val r =
        parse("[Strange-Raw] 坂本日常 第一季 / SAKAMOTO DAYS S01 [08] [Bilibili] [WEB-DL] [1080P AVC-8Bits AAC 2.0] [简日内嵌字幕]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690537_1_08_HEVC_x265_10bit_1080p`() {
    kotlin.run {
    val r = parse("[云歌字幕组][1月新番][欢迎来到日本，精灵小姐][08][HEVC][x265 10bit][1080p][简日双语]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690536_VCB_Studio_Sword_Art_Online_10_bit_1080p_HEVC_BDRip_S1_2_EE_Reseed_Fin`() {
    kotlin.run {
    val r =
        parse("[VCB-Studio] 刀剑神域 / Sword Art Online / ソードアート・オンライン 10-bit 1080p HEVC BDRip [S1-2+EE+序列之争 Reseed Fin]")
    assertEquals("01..02", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690535_LoliHouse_Ameku_Takao_s_Detective_Karte___07_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[悠哈璃羽字幕社&LoliHouse] 天久鹰央的推理病历表 / Ameku Takao's Detective Karte - 07 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690533_LoliHouse_S_Beheneko___10_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] S级怪兽《贝希摩斯》被误认成小猫，成为精灵女孩的骑士（宠物）一起生活 / Beheneko - 10 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("10..10", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690531_You_and_Idol_Precure_1080p_05_Q`() {
    kotlin.run {
    val r =
        parse("[雪飘工作室][キミとアイドルプリキュア♪/You and Idol Precure♪/与你同为 偶像光之美少女♪][1080p][05][简繁外挂](检索:Q娃)")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public fun `690530_You_and_Idol_Precure_720p_05_Q`() {
    kotlin.run {
    val r =
        parse("[雪飄工作室][キミとアイドルプリキュア♪/You and Idol Precure♪/与你同为 偶像光之美少女♪][720p][05][繁體內嵌](檢索:Q娃)")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("720P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690529_You_and_Idol_Precure_720p_05_Q`() {
    kotlin.run {
    val r =
        parse("[雪飘工作室][キミとアイドルプリキュア♪/You and Idol Precure♪/与你同为 偶像光之美少女♪][720p][05][简体内嵌](检索:Q娃)")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("720P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690528_DBFC_020end_1080P_MP4`() {
    kotlin.run {
    val r = parse("【DBFC字幕组】【龙珠 大魔】【020end】【中日双语字幕】【1080P】【MP4】")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690527_______09_CR_WebRip_AI2160p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r = parse("[不当舔狗制作组] 我独自升级 第二季 -起于暗影- - 09 [CR WebRip AI2160p HEVC-10bit AAC][官方简繁内封字幕]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690526_MingY__That_s_Journey__Zatsu_Tabi_That_s_Journey_01_WebRip`() {
    kotlin.run {
    val r =
        parse("[MingY] 隨興旅 -That's Journey- / Zatsu Tabi: That's Journey [01][先行版][WebRip][繁日內嵌]（檢索：雜旅）")
    assertEquals("01..01", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690525_YUI_Yami_Shibai_14_08_x264_1080p_CHT`() {
    kotlin.run {
    val r = parse("【悠哈璃羽字幕社&西農YUI漢化組】[暗芝居 第十四季_Yami Shibai 14][08][x264 1080p][CHT]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690524_MingY__That_s_Journey__Zatsu_Tabi_That_s_Journey_01_WebRip`() {
    kotlin.run {
    val r =
        parse("[MingY] 随兴旅 -That's Journey- / Zatsu Tabi: That's Journey [01][先行版][WebRip][简日内嵌]（检索：杂旅）")
    assertEquals("01..01", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690523_YUI_Yami_Shibai_14_08_x264_1080p_CHS`() {
    kotlin.run {
    val r = parse("【悠哈璃羽字幕社&西农YUI汉化组】[暗芝居 第十四季_Yami Shibai 14][08][x264 1080p][CHS]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690522_LoliHouse_____Ore_dake_Level_Up_na_Ken_Solo_Leveling___21_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 我独自升级 -起于暗影- / Ore dake Level Up na Ken / Solo Leveling - 21 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("21..21", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690517__21_21_MIRAI_DAYS_Maho_Girls_Precure_21_21__21_21_1080p_08_Q`() {
    kotlin.run {
    val r =
        parse("[雪飘工作室][魔法つかいプリキュア!!~MIRAI DAYS~/Maho_Girls_Precure!!/魔法少女 光之美少女!!~未来的日子~][1080p][08][简繁外挂](检索:Q娃)")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public fun `690516__21_21_MIRAI_DAYS_Maho_Girls_Precure_21_21__21_21_720p_08_Q`() {
    kotlin.run {
    val r =
        parse("[雪飘工作室][魔法つかいプリキュア!!~MIRAI DAYS~/Maho_Girls_Precure!!/魔法少女 光之美少女!!~未来的日子~][720p][08][简体内嵌](检索:Q娃)")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("720P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690515__21_21_MIRAI_DAYS_Maho_Girls_Precure_21_21__21_21_720p_08_Q`() {
    kotlin.run {
    val r =
        parse("[雪飄工作室][魔法つかいプリキュア!!~MIRAI DAYS~/Maho_Girls_Precure!!/魔法少女 光之美少女!!~未來的日子~][720p][08][繁體內嵌](檢索:Q娃)")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("720P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690512_10_20_1080P_MP4`() {
    kotlin.run {
    val r = parse("【动漫国字幕组&澄空学园&华盟字幕社&千夏字幕组】★10月新番[冻牌][20][1080P][简体][MP4]")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690511_10_20_1080P_MP4`() {
    kotlin.run {
    val r = parse("【動漫國字幕組&澄空學園&華盟字幕社&千夏字幕組】★10月新番[凍牌][20][1080P][繁體][MP4]")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690510_Ao_no_Exorcist_Yosuga_Hen_09_1080P_MP4`() {
    kotlin.run {
    val r = parse("【豌豆字幕组】[青之驱魔师 终夜篇 / Ao no Exorcist _Yosuga Hen][09][简体][1080P][MP4]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690509_Ao_no_Exorcist_Yosuga_Hen_09_1080P_MP4`() {
    kotlin.run {
    val r = parse("【豌豆字幕組】[青之驅魔師 終夜篇 / Ao no Exorcist _Yosuga Hen][09][繁體][1080P][MP4]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690508___08_CR_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r = parse("[不当舔狗制作组] 药屋少女的呢喃 第二季 - 08 [CR WebRip 1080p HEVC-10bit AAC][官方简繁内封字幕]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690507_1155_2_WEBRIP_MKV_1080P`() {
    kotlin.run {
    val r = parse("[银色子弹字幕组][名侦探柯南][第1155集 追踪！侦探出租车2][WEBRIP][简繁日多语MKV][1080P]")
    assertEquals("1155..1155", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690506_1155_2_WEBRIP_MP4_1080P`() {
    kotlin.run {
    val r = parse("[银色子弹字幕组][名侦探柯南][第1155集 追蹤！偵探計程車2][WEBRIP][繁日雙語MP4][1080P]")
    assertEquals("1155..1155", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690505_1155_2_WEBRIP_MP4_1080P`() {
    kotlin.run {
    val r = parse("[银色子弹字幕组][名侦探柯南][第1155集 追踪！侦探出租车2][WEBRIP][简日双语MP4][1080P]")
    assertEquals("1155..1155", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690504_Skymoon_Raws_____Ore_dake_Level_Up_na_Ken_Season_2__09_ViuTV_WEB_DL_1080p_AVC_AAC`() {
    kotlin.run {
    val r =
        parse("[Skymoon-Raws] 我獨自升級 第二季 -起於闇影- / Ore dake Level Up na Ken Season 2- 09 [ViuTV][WEB-DL][1080p][AVC AAC]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690503_SAKAMOTO_DAYS_S01E09_All_Aboard_1080p_NF_WEB_DL_DDP5_1_H_264_MULTi_VARYG_Multi_Audio_Multi_Subs`() {
    kotlin.run {
    val r =
        parse("SAKAMOTO DAYS S01E09 All Aboard 1080p NF WEB-DL DDP5.1 H 264 MULTi-VARYG (Multi-Audio, Multi-Subs)")
    assertEquals("264..264", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690502_Babanba_Banban_Vampire___08_NetFlix_1920x1080_AVC_AAC_MKV`() {
    kotlin.run {
    val r = parse("[天月搬運組] 吸吸吸吸吸血鬼 / Babanba Banban Vampire - 08 (NetFlix 1920x1080 AVC AAC MKV)")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690501_ANi___09_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] 在沖繩喜歡上的女孩方言講得太過令人困擾 - 09 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690500_ANi_Blue_Exorcist___09_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] Blue Exorcist / 青之驅魔師 終夜篇 - 09 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690499_Sub_BanG_Dream_21_Ave_Mujica_08_1080P_AVC_AAC_WebRip`() {
    kotlin.run {
    val r = parse("[霜庭雲花Sub][BanG Dream! Ave Mujica][08][1080P][AVC AAC][繁日雙語][WebRip]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690498_Sub_BanG_Dream_21_Ave_Mujica_08_1080P_AVC_AAC_WebRip`() {
    kotlin.run {
    val r = parse("[霜庭云花Sub][BanG Dream! Ave Mujica][08][1080P][AVC AAC][简日双语][WebRip]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690497___20_CR_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r = parse("[不当舔狗制作组] 龙珠大魔 - 20 [CR WebRip 1080p HEVC-10bit AAC][官方简繁内封字幕]")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690496_LoliHouse_Ishura_S2___08_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[喵萌奶茶屋&LoliHouse] 异修罗 第二季 / Ishura S2 - 08 [WebRip 1080p HEVC-10bit AAC][简繁日内封字幕]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690495_ANi_A___08_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] 離開 A 級隊伍的我，和從前的弟子往迷宮深處邁進 - 08 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690494_ANi___09_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] 我與尼特女忍者的莫名同居生活 - 09 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690493_ANi_Solo_Leveling_S02___21_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[ANi] Solo Leveling S02 / 我獨自升級 第二季 －起於闇影－ - 21 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("21..21", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690492_LoliHouse_Myth_of_Emotions_Sousei_no_Aquarion_Myth_of_Emotions___08_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 想星的大天使 Myth of Emotions / Sousei no Aquarion: Myth of Emotions - 08 [WebRip 1080p HEVC-10bit AAC][无字幕]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690491_jibaketa_TVB_Shinkalion_Change_the_World___31_WEB_1920x1080_AVC_AAC_SRT_TVB_YUE_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][TVB粵語]新幹線戰士 改變世界 / 新幹線變形機器人 變革世代 / Shinkalion Change the World - 31 [粵語+內封繁體中文字幕][WEB 1920x1080 AVC AAC SRT TVB YUE CHT]")
    assertEquals("31..31", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690490_Skymoon_Raws_S_Neko_to_Machigawarete_Elf_Musume_no_Pet_toshite_Kurashitemasu___10_ViuTV_WEB_DL_1080p_AVC_AAC`() {
    kotlin.run {
    val r =
        parse("[Skymoon-Raws] S級怪獸《貝希摩斯》被誤認成小貓，成為精靈女孩的騎士（寵物）一起生活 / Neko to Machigawarete Elf Musume no Pet toshite Kurashitemasu - 10 [ViuTV][WEB-DL][1080p][AVC AAC]")
    assertEquals("10..10", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690489_LoliHouse_Ao_no_Hako_Blue_Box___20_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[喵萌奶茶屋&LoliHouse] 青箱 / Ao no Hako / Blue Box - 20 [WebRip 1080p HEVC-10bit AAC][简繁日内封字幕]")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690488_10_Ao_no_Hako_Blue_Box_20_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★10月新番★[青箱 / Ao no Hako / Blue Box][20][1080p][繁日雙語][招募翻譯]")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690487_ANi_S___10_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[ANi] S 級怪獸《貝希摩斯》被誤認成小貓，成為精靈女孩的騎士（寵物）一起生活 - 10 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("10..10", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690486_A_Doraemon1979_1998_3_7_1080P_WEB_A_MKV`() {
    kotlin.run {
    val r =
        parse("[哆啦字幕组][哆啦A梦大山版附映中篇 Doraemon1979][1998.3.7][1080P][WEB][简日&繁日][哆啦A梦归来了][数码修复版][MKV]")
    assertEquals("null", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690485_60_WEBRIP_MP4_MP4_MKV_1080P`() {
    kotlin.run {
    val r = parse("[银色子弹字幕组][名侦探柯南][第60集 插画家杀人事件][WEBRIP][简日双语MP4/繁日雙語MP4/简繁日多语MKV][1080P]")
    assertEquals("60..60", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690484_GM_Team_5_Fights_Break_Sphere_2022_136_HEVC_GB_4K`() {
    kotlin.run {
    val r = parse("[GM-Team][国漫][斗破苍穹 第5季][Fights Break Sphere Ⅴ][2022][136][HEVC][GB][4K]")
    assertEquals("136..136", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690483_GM_Team_5_Fights_Break_Sphere_2022_136_AVC_GB_1080P`() {
    kotlin.run {
    val r = parse("[GM-Team][国漫][斗破苍穹 第5季][Fights Break Sphere Ⅴ][2022][136][AVC][GB][1080P]")
    assertEquals("136..136", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690482_LoliHouse_Hana_wa_Saku_Shura_no_Gotoku___08_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[喵萌奶茶屋&LoliHouse] 群花绽放，仿如修罗 / Hana wa Saku Shura no Gotoku - 08 [WebRip 1080p HEVC-10bit AAC][简繁日内封字幕]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690481_V_V_Chou_Denji_Machine_Voltes_V_1977_TV_01_40_Fin_1080p_REV`() {
    kotlin.run {
    val r =
        parse("[白恋字幕组][超电磁侠波鲁吉斯V][超電磁マシーン ボルテスV][Chou Denji Machine Voltes V][1977][TV 01-40 Fin][1080p][内封简中字幕][REV]")
    assertEquals("01..40", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690479___08_CR_WebRip_AI2160p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r = parse("[不当舔狗制作组] 药屋少女的呢喃 第二季 - 08 [CR WebRip AI2160p HEVC-10bit AAC][官方简繁内封字幕]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690478_136_WEB_MKV_2160P`() {
    kotlin.run {
    val r = parse("[斗破苍穹年番][第136集][国语中字][WEB-MKV][2160P]")
    assertEquals("136..136", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690477___20_CR_WebRip_AI2160p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r = parse("[不当舔狗制作组] 龙珠大魔 - 20 [CR WebRip AI2160p HEVC-10bit AAC][官方简繁内封字幕]")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690474_LoliHouse_Fuguushoku_Kanteishi_ga_Jitsu_wa_Saikyou_Datta___08_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 不幸职业【鉴定士】实则最强 / Fuguushoku 'Kanteishi' ga Jitsu wa Saikyou Datta - 08 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690473_1_BanG_Dream_21_Ave_Mujica_08_1080p_MP4`() {
    kotlin.run {
    val r = parse("[漫猫字幕组][1月新番][BanG Dream! Ave Mujica][08][1080p][MP4][简中]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690472_1_BanG_Dream_21_Ave_Mujica_08_1080p_MP4`() {
    kotlin.run {
    val r = parse("[漫貓字幕組][1月新番][BanG Dream! Ave Mujica][08][1080p][MP4][繁中]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690471_1_BanG_Dream_21_Ave_Mujica_07_1080p_MP4`() {
    kotlin.run {
    val r = parse("[漫猫字幕组][1月新番][BanG Dream! Ave Mujica][07][1080p][MP4][简中]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690470_1_BanG_Dream_21_Ave_Mujica_07_1080p_MP4`() {
    kotlin.run {
    val r = parse("[漫貓字幕組][1月新番][BanG Dream! Ave Mujica][07][1080p][MP4][繁中]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690469_ANi_Blue_Miburo___20_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] Blue Miburo / 青之壬生浪 - 20 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690468_LoliHouse_Shangri_La_Frontier___45_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] Shangri-La Frontier / 香格里拉·开拓异境～粪作猎手挑战神作～ - 45 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("45..45", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690467_132_WEB_MKV_2160P`() {
    kotlin.run {
    val r = parse("[凡人修仙传][第132集][国语中字][WEB-MKV][2160P]")
    assertEquals("132..132", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690466_SweetSub_Momentary_Lily_08_WebRip_1080P_AV1_10bit`() {
    kotlin.run {
    val r = parse("[SweetSub][刹那之花][Momentary Lily][08][WebRip][1080P][AV1 10bit][简日内封]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690465_LoliHouse_Dragon_Ball_Daima___20_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 龙珠 大魔 / Dragon Ball Daima - 20 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690464_10_Ao_no_Hako_Blue_Box_20_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★10月新番★[青箱 / Ao no Hako / Blue Box][20][1080p][简日双语][招募翻译]")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690463_01_Zenshuu_ZENSHU_07_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[全修。 / Zenshuu. / ZENSHU][07][1080p][繁日雙語]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690462_SweetSub_Momentary_Lily_07_WebRip_1080P_AV1_10bit`() {
    kotlin.run {
    val r = parse("[SweetSub][刹那之花][Momentary Lily][07][WebRip][1080P][AV1 10bit][简日内封]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690461_SweetSub_Momentary_Lily_06_WebRip_1080P_AV1_10bit`() {
    kotlin.run {
    val r = parse("[SweetSub][刹那之花][Momentary Lily][06][WebRip][1080P][AV1 10bit][简日内封]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690460_SweetSub_Momentary_Lily_05_WebRip_1080P_AV1_10bit`() {
    kotlin.run {
    val r = parse("[SweetSub][刹那之花][Momentary Lily][05][WebRip][1080P][AV1 10bit][简日内封]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690459_SweetSub_Momentary_Lily_04_WebRip_1080P_AV1_10bit`() {
    kotlin.run {
    val r = parse("[SweetSub][刹那之花][Momentary Lily][04][WebRip][1080P][AV1 10bit][简日内封]")
    assertEquals("04..04", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690458_SweetSub_Momentary_Lily_03_WebRip_1080P_AV1_10bit`() {
    kotlin.run {
    val r = parse("[SweetSub][刹那之花][Momentary Lily][03][WebRip][1080P][AV1 10bit][简日内封]")
    assertEquals("03..03", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690457_SweetSub_Momentary_Lily_02_WebRip_1080P_AV1_10bit`() {
    kotlin.run {
    val r = parse("[SweetSub][刹那之花][Momentary Lily][02][WebRip][1080P][AV1 10bit][简日内封]")
    assertEquals("02..02", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690456_jibaketa_Dandadan___10_WEB_1920x1080_AVC_AACx2_SRT_MUSE_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成][代理商粵語]膽大黨 / Dandadan - 10 [粵日雙語+內封繁體中文字幕](WEB 1920x1080 AVC AACx2 SRT MUSE CHT)")
    assertEquals("10..10", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690455_SweetSub_Momentary_Lily_01_WebRip_1080P_AV1_10bit`() {
    kotlin.run {
    val r = parse("[SweetSub][刹那之花][Momentary Lily][01][WebRip][1080P][AV1 10bit][简日内封]")
    assertEquals("01..01", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690454_Ao_no_Hako_Blue_Box_19_WebRip_HEVC_AAC`() {
    kotlin.run {
    val r =
        parse("[北宇治字幕组] 青之箱 / 青春之箱 / 青春盒子 / 蓝箱 / Ao no Hako / Blue Box [19][WebRip][HEVC_AAC][简繁日内封]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690453_Ao_no_Hako_Blue_Box_19_WebRip_HEVC_AAC`() {
    kotlin.run {
    val r = parse("[北宇治字幕组] 青之箱 / 蓝箱 / Ao no Hako / Blue Box [19][WebRip][HEVC_AAC][简日内嵌]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690452_Ao_no_Hako_Blue_Box_19_WebRip_HEVC_AAC`() {
    kotlin.run {
    val r = parse("[北宇治字幕组] 青春之箱 / 青春盒子 / Ao no Hako / Blue Box [19][WebRip][HEVC_AAC][繁日內嵌]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690451_Babanba_Banban_Vampire_03_1080P_CHS`() {
    kotlin.run {
    val r = parse("【悠哈璃羽字幕社】[嘻嘻嘻嘻吸血鬼][Babanba Banban Vampire][03][1080P][CHS]")
    assertEquals("03..03", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690450_Ameku_Takao_no_Suiri_Karte_07_1080p_CHT`() {
    kotlin.run {
    val r = parse("【悠哈璃羽字幕社】[天久鷹央的推理病歷表_Ameku Takao no Suiri Karte][07][1080p][CHT]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690449_Ameku_Takao_no_Suiri_Karte_07_1080p_CHS`() {
    kotlin.run {
    val r = parse("【悠哈璃羽字幕社】[天久鹰央的推理病历表_Ameku Takao no Suiri Karte][07][1080p][CHS]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690447_Amor_Kusuriya_no_Hitorigoto_32_WEBDL_1080P_MKV`() {
    kotlin.run {
    val r = parse("[Amor压制组][药师少女的独语 藥師少女的獨語 Kusuriya no Hitorigoto][32][简繁内封][WEBDL][1080P][MKV]")
    assertEquals("32..32", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690445_GM_Team_Fan_Ren_Xiu_Xian_Zhuan_2024_01_08_AVC_GB_1080P`() {
    kotlin.run {
    val r = parse("[GM-Team][国漫][凡人修仙传 外海风云][Fan Ren Xiu Xian Zhuan][2024][01-08][AVC][GB][1080P]")
    assertEquals("01..08", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690444_ANi___08_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[搬運][ANi] 中年大叔轉生反派千金 - 08 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690443_08_GB_CN_HEVC_opus_1080p`() {
    kotlin.run {
    val r = parse("【极影字幕·毁片党】 这家公司里有我喜欢的人 第08集 GB_CN HEVC_opus 1080p")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690442_LoliHouse_Akuyaku_Reijou_Tensei_Ojisan___08_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 大叔转生恶役大小姐 / Akuyaku Reijou Tensei Ojisan - 08 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690441_ANi___09_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[搬運][ANi] 我和班上最討厭的女生結婚了。 - 09 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690440_01_Tasokare_Hotel_09_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[黄昏旅店 / Tasokare Hotel][09][1080p][简日双语][招募翻译]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690438_Skymoon_Raws_Dragonball_DaiMa___20_ViuTV_WEB_DL_1080p_AVC_AAC`() {
    kotlin.run {
    val r = parse("[Skymoon-Raws] 龍珠大魔 / Dragonball DaiMa - 20 [ViuTV][WEB-DL][1080p][AVC AAC]")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690436_Skymoon_Raws_Guild_no_Uketsukejou_desu_ga_Zangyou_wa_Iya_nanode_Boss_wo_Solo_Toubatsu_Shiyou_to_Omoimasu___08_ViuTV_WEB_DL_1080p_AVC_AAC`() {
    kotlin.run {
    val r =
        parse("[Skymoon-Raws] 雖然是公會的櫃檯小姐，但因為不想加班所以打算獨自討伐迷宮頭目 / Guild no Uketsukejou desu ga, Zangyou wa Iya nanode Boss wo Solo Toubatsu Shiyou to Omoimasu - 08 [ViuTV][WEB-DL][1080p][AVC AAC]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690435_Kusuriya_no_Hitorigoto_32_1080P_MP4`() {
    kotlin.run {
    val r = parse("【豌豆字幕组】[药屋少女的呢喃（药师少女的独语）/ Kusuriya no Hitorigoto][32][简体][1080P][MP4]")
    assertEquals("32..32", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690434_Kusuriya_no_Hitorigoto_32_1080P_MP4`() {
    kotlin.run {
    val r = parse("【豌豆字幕組】[藥屋少女的呢喃（藥師少女的獨語）/ Kusuriya no Hitorigoto][32][繁體][1080P][MP4]")
    assertEquals("32..32", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690433_GM_Team_5_Fights_Break_Sphere_2022_131_135_HEVC_GB_4K`() {
    kotlin.run {
    val r = parse("[GM-Team][国漫][斗破苍穹 第5季][Fights Break Sphere Ⅴ][2022][131-135][HEVC][GB][4K]")
    assertEquals("131..135", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690432_GM_Team_5_Fights_Break_Sphere_2022_131_135_AVC_GB_1080P`() {
    kotlin.run {
    val r = parse("[GM-Team][国漫][斗破苍穹 第5季][Fights Break Sphere Ⅴ][2022][131-135][AVC][GB][1080P]")
    assertEquals("131..135", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690431_GM_Team_Tales_of_Qin_Mu_2024_15_19_AVC_GB_1080P`() {
    kotlin.run {
    val r = parse("[GM-Team][国漫][牧神记][Tales of Qin Mu][2024][15-19][AVC][GB][1080P]")
    assertEquals("15..19", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690430_GM_Team_Soul_Land_The_Peerless_Tang_Clan_2023_85_90_AVC_GB_1080P`() {
    kotlin.run {
    val r =
        parse("[GM-Team][国漫][斗罗大陆Ⅱ 绝世唐门][Soul Land Ⅱ：The Peerless Tang Clan][2023][85-90][AVC][GB][1080P]")
    assertEquals("85..90", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690429_ANi___20_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] 凍牌~地下麻將鬥牌錄~ - 20 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690428_GM_Team_2_The_Demon_Hunter_2024_09_14_GB_4K_HEVC_10Bit`() {
    kotlin.run {
    val r = parse("[GM-Team][国漫][沧元图 第2季][The Demon Hunter Ⅱ][2024][09-14][GB][4K HEVC 10Bit]")
    assertEquals("09..14", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690427_GM_Team_2_Tomb_of_Fallen_Gods_2024_27_END_GB_4K_HEVC_10Bit`() {
    kotlin.run {
    val r = parse("[GM-Team][国漫][神墓 第2季][Tomb of Fallen Gods Ⅱ][2024][27 END][GB][4K HEVC 10Bit]")
    assertEquals("27..27", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690426_GM_Team_2_Tomb_of_Fallen_Gods_2024_26_GB_4K_HEVC_10Bit`() {
    kotlin.run {
    val r = parse("[GM-Team][国漫][神墓 第2季][Tomb of Fallen Gods Ⅱ][2024][26][GB][4K HEVC 10Bit]")
    assertEquals("26..26", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690425_GM_Team_4_Rakshasa_Street_2024_11_16_AVC_GB_1080P`() {
    kotlin.run {
    val r = parse("[GM-Team][国漫][镇魂街 第4季 天武风雷篇][Rakshasa Street Ⅳ][2024][11-16][AVC][GB][1080P]")
    assertEquals("11..16", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690424_GM_Team_Perfect_World_2021_200_204_AVC_GB_1080P`() {
    kotlin.run {
    val r = parse("[GM-Team][国漫][完美世界][Perfect World][2021][200-204][AVC][GB][1080P]")
    assertEquals("200..204", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690423_GM_Team_My_Senior_Brother_is_Too_Steady_2023_74_78_GB_4K_HEVC_10Bit`() {
    kotlin.run {
    val r =
        parse("[GM-Team][国漫][师兄啊师兄][My Senior Brother is Too Steady][2023][74-78][GB][4K HEVC 10Bit]")
    assertEquals("74..78", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690422_GM_Team_Throne_of_Seal_2022_144_148_AVC_GB_1080P`() {
    kotlin.run {
    val r = parse("[GM-Team][国漫][神印王座][Throne of Seal][2022][144-148][AVC][GB][1080P]")
    assertEquals("144..148", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690421_GM_Team_Shrouding_the_Heavens_2023_94_98_AVC_GB_1080P`() {
    kotlin.run {
    val r = parse("[GM-Team][国漫][遮天][Shrouding the Heavens][2023][94-98][AVC][GB][1080P]")
    assertEquals("94..98", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690420_ANi_Sorairo_Utility___09_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] Sorairo Utility / 一桿青空 - 09 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690419_ANi___08_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[ANi] 雖然是公會的櫃檯小姐，但因為不想加班所以打算獨自討伐迷宮頭目 - 08 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690418_01_Ishura_S2_08_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[異修羅 第二季 / Ishura S2][08][1080p][繁日雙語][招募翻譯]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690417_Kusuriya_no_Hitorigoto___32v2_NetFlix_1920x1080_AVC_AAC_MKV`() {
    kotlin.run {
    val r =
        parse("[天月搬運組] 藥師少女的獨語 (藥屋少女的呢喃) / Kusuriya no Hitorigoto - 32v2 (NetFlix 1920x1080 AVC AAC MKV)")
    assertEquals("32..32", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690416_Kusuriya_no_Hitorigoto___32_NetFlix_1920x1080_AVC_AAC_MKV`() {
    kotlin.run {
    val r =
        parse("[天月搬運組] 藥師少女的獨語 (藥屋少女的呢喃) / Kusuriya no Hitorigoto - 32 (NetFlix 1920x1080 AVC AAC MKV)")
    assertEquals("32..32", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690415_jibaketa_TVB_Dog_Signal___10_WEB_1920x1080_x264_AAC_SRT_TVB_YUE_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&二次壓制][TVB粵語]愛犬訊號 / Dog Signal - 10 [粵語+內封繁體中文字幕][WEB 1920x1080 x264 AAC SRT TVB YUE CHT]")
    assertEquals("10..10", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690413_Unnamed_Memory_Act_2_20_1080p_2025_1`() {
    kotlin.run {
    val r = parse("[猎户手抄部] 无名记忆 第二季 / Unnamed Memory Act.2 [20] [1080p] [繁日内嵌] [2025年1月番]")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690412_A_Doraemon1979_1998_3_7_2080P_4K_WEB_A_MP4`() {
    kotlin.run {
    val r =
        parse("[哆啦字幕組][哆啦A夢大山版附映中篇Doraemon1979][1998.3.7][2080P（准4K）][WEB][繁日雙語][哆啦A夢歸來了][数位修復版][MP4]")
    assertEquals("null", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690411_A_Doraemon1979_1998_3_7_2080P_4K_WEB_A_MP4`() {
    kotlin.run {
    val r =
        parse("[哆啦字幕组][哆啦A梦大山版附映中篇 Doraemon1979][1998.3.7][2080P（准4K）][WEB][简日双语][哆啦A梦归来了][数码修复版][MP4]")
    assertEquals("null", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690410_Unnamed_Memory_Act_2_20_1080p_2025_1`() {
    kotlin.run {
    val r = parse("[猎户手抄部] 无名记忆 第二季 / Unnamed Memory Act.2 [20] [1080p] [简日内嵌] [2025年1月番]")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690409_SweetSub_Momentary_Lily_09_WebRip_1080P_AVC_8bit`() {
    kotlin.run {
    val r = parse("[SweetSub][刹那之花][Momentary Lily][09][WebRip][1080P][AVC 8bit][简体内嵌]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690408_ANi_Farmagia_FARMAGIA___08_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] Farmagia / FARMAGIA 魔農傳記 - 08 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690407_ANi_Tasokare_Hotel___09_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] Tasokare Hotel / 黃昏旅店 - 09 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690406_____Ore_dake_Level_Up_na_Ken_S2_20_1080p_KoVer_2025_1`() {
    kotlin.run {
    val r =
        parse("[猎户压制部] 我独自升级 第二季 -起于暗影- / Ore dake Level Up na Ken S2 [20] [1080p] [繁日内嵌] [KoVer] [2025年1月番]")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690405_____Ore_dake_Level_Up_na_Ken_S2_20_1080p_KoVer_2025_1`() {
    kotlin.run {
    val r =
        parse("[猎户压制部] 我独自升级 第二季 -起于暗影- / Ore dake Level Up na Ken S2 [20] [1080p] [简日内嵌] [KoVer] [2025年1月番]")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690404_ANi_Welcome_to_Japan_Ms_Elf___08_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[ANi] Welcome to Japan Ms Elf / 歡迎來到日本，妖精小姐。 - 08 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690403_Re_Re_Zero_kara_Hajimeru_Isekai_Seikatsu_62_WebRip_1080p_AVC_AAC`() {
    kotlin.run {
    val r =
        parse("【百冬練習組】【Re: 從零開始的異世界的生活_Re꞉ Zero kara Hajimeru Isekai Seikatsu】[62][WebRip 1080p AVC AAC][繁體]")
    assertEquals("62..62", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690402_Re_Re_Zero_kara_Hajimeru_Isekai_Seikatsu_62_WebRip_1080p_AVC_AAC`() {
    kotlin.run {
    val r =
        parse("【百冬练习组】【Re: 从零开始的异世界的生活_Re꞉ Zero kara Hajimeru Isekai Seikatsu】[62][WebRip 1080p AVC AAC][简体]")
    assertEquals("62..62", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690401_4_16_WEB_MKV_2160P`() {
    kotlin.run {
    val r = parse("[镇魂街 第4季][第16集][国语中字][WEB-MKV][2160P]")
    assertEquals("16..16", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690400_2_90_WEB_MKV_2160P`() {
    kotlin.run {
    val r = parse("[斗罗大陆2绝世唐门][第90集][国语中字][WEB-MKV][2160P]")
    assertEquals("90..90", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690399_LoliHouse_Kono_Kaisha_ni_Suki_na_Hito_ga_Imasu___07_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 这公司有我喜欢的人 / この会社に好きな人がいます / Kono Kaisha ni Suki na Hito ga Imasu - 07 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690398_LoliHouse_____Rurouni_Kenshin_2023___44_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 浪客剑心 -明治剑客浪漫谭- 京都动乱 / Rurouni Kenshin (2023) - 44 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("44..44", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690389_BanG_Dream_21_Ave_Mujica_07_1080p`() {
    kotlin.run {
    val r = parse("[萌樱字幕组] BanG Dream! Ave Mujica [07][1080p]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690388_Chibi_Maruko_chan_II_1468_2025_02_23_BIG5_1080P_MP4`() {
    kotlin.run {
    val r =
        parse("[丸子家族][櫻桃小丸子第二期(Chibi Maruko-chan II)][1468]小丸子想沾花輪的光&友藏想疼愛宏誌[2025.02.23][BIG5][1080P][MP4]")
    assertEquals("1468..1468", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690387_Chibi_Maruko_chan_II_1468_2025_02_23_GB_1080P_MP4`() {
    kotlin.run {
    val r =
        parse("[丸子家族][樱桃小丸子第二期(Chibi Maruko-chan II)][1468]小丸子想沾花轮的光&友藏想疼爱宏志[2025.02.23][GB][1080P][MP4]")
    assertEquals("1468..1468", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690386_VCB_Studio_Yuru_Camp_Season_3_SEASON3_10_bit_1080p_HEVC_BDRip_Fin`() {
    kotlin.run {
    val r =
        parse("[千夏字幕组&VCB-Studio] 摇曳露营△ 第三季 / Yuru Camp Season 3 / ゆるキャン△ SEASON3 10-bit 1080p HEVC BDRip [Fin]")
    assertEquals("03..03", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690385_BanG_Dream_21_Ave_Mujica___09_CR_WebRip_AI2160p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[不当舔狗制作组] BanG Dream! Ave Mujica - 09 [CR WebRip AI2160p HEVC-10bit AAC][官方简繁内封字幕]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690384_BanG_Dream_21_Ave_Mujica___09_CR_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[不当舔狗制作组] BanG Dream! Ave Mujica - 09 [CR WebRip 1080p HEVC-10bit AAC][官方简繁内封字幕]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690383_V_DanMachi_V_12_1080P_MKV`() {
    kotlin.run {
    val r = parse("【鈴風字幕組】【在地下城尋求邂逅是否搞錯了什麼 V/DanMachi V】[12][1080P][MKV][繁體外掛]")
    assertEquals("12..12", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public fun `690382_083_085_1080P_MP4`() {
    kotlin.run {
    val r = parse("【枫叶字幕组】[宠物小精灵 / 宝可梦 地平线 烈空坐飞升][083-085][简体][1080P][MP4]")
    assertEquals("83..85", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690381_083_085_1080P_MP4`() {
    kotlin.run {
    val r = parse("【楓葉字幕組】[寵物小精靈 / 寶可夢 地平線 烈空坐飛升][083-085][繁體][1080P][MP4]")
    assertEquals("83..85", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690379_Ao_no_Hako_22_WEBrip_1080P`() {
    kotlin.run {
    val r = parse("【今晚月色真美】[青之箱 / アオのハコ / Ao no Hako][22][WEBrip][1080P][简日双语]")
    assertEquals("22..22", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690378_TensoRaws_BanG_Dream_21_Ave_Mujica_09_WEBRip_2160p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r = parse("[TensoRaws] BanG Dream! Ave Mujica [09] [WEBRip 2160p HEVC-10bit AAC]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690377_GalaxyRailroad_888_GO_RUSH_21_21_Yu_Gi_Oh_21_GO_RUSH__21_21_146_720P_GB`() {
    kotlin.run {
    val r = parse("[GalaxyRailroad-888] 游戏王GO RUSH!! Yu-Gi-Oh! GO RUSH !! 146 720P [GB_简中]")
    assertEquals("146..146", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("720P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690376_Skymoon_Raws_Akuyaku_Reijou_Tensei_Ojisan___08_ViuTV_WEB_DL_1080p_AVC_AAC`() {
    kotlin.run {
    val r =
        parse("[Skymoon-Raws] 中年大叔轉生反派千金 / Akuyaku Reijou Tensei Ojisan - 08 [ViuTV][WEB-DL][1080p][AVC AAC]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690375_Production_01_BanG_Dream_21_Ave_Mujica_09_1080p`() {
    kotlin.run {
    val r = parse("【喵萌Production】★01月新番★[BanG Dream! Ave Mujica][09][1080p][简日双语][招募翻译]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690374_ANi_Rurouni_Kenshin___20_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[ANi] Rurouni Kenshin / 神劍闖江湖 ―明治劍客浪漫譚― 京都動亂 - 20 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690373_ANi_Anyway_Im_Falling_in_Love_with_You___08_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[ANi] Anyway Im Falling in Love with You / 終究，與你相戀。 - 08 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690372_ANi_Trillion_Game___21_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] Trillion Game / 一兆＄遊戲 - 21 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("21..21", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690371_Zenshuu_08_1080p`() {
    kotlin.run {
    val r = parse("[桜都字幕组] 全修。 / Zenshuu. [08][1080p][简繁内封]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690370_Zenshuu_08_1080p`() {
    kotlin.run {
    val r = parse("[桜都字幕組] 全修。 / Zenshuu. [08][1080p][繁體內嵌]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690369_Zenshuu_08_1080p`() {
    kotlin.run {
    val r = parse("[桜都字幕组] 全修。 / Zenshuu. [08][1080p][简体内嵌]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690368_Billion_Meta_Lab_Momentary_Lily_07_1080P_HEVC_10bit`() {
    kotlin.run {
    val r =
        parse("[Billion Meta Lab] 昙花一现的百合 Momentary Lily [07][1080P][HEVC 10bit][简繁日内封][检索：刹那之花]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690367_Billion_Meta_Lab_Momentary_Lily_07_1080P`() {
    kotlin.run {
    val r = parse("[Billion Meta Lab] 曇花一現的百合 Momentary Lily [07][1080P][繁日內嵌][檢索：剎那之花]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690366_Billion_Meta_Lab_Momentary_Lily_07_1080P`() {
    kotlin.run {
    val r = parse("[Billion Meta Lab] 昙花一现的百合 Momentary Lily [07][1080P][简日内嵌][检索：刹那之花]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690365_ANi_Momentary_Lily_MOMENTARY_LILY___09_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[ANi] Momentary Lily / MOMENTARY LILY 剎那之花 - 09 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690364_01_Hana_wa_Saku_Shura_no_Gotoku_08_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[群花綻放，彷如修羅 / Hana wa Saku Shura no Gotoku][08][1080p][繁日雙語][招募翻譯]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690363_01_Hana_wa_Saku_Shura_no_Gotoku_08_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[群花绽放，彷如修罗 / Hana wa Saku Shura no Gotoku][08][1080p][简日双语][招募翻译]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690362_01_Ishura_S2_08_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[异修罗 第二季 / Ishura S2][08][1080p][简日双语][招募翻译]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690361_jibaketa_TVB_Dog_Signal___09_WEB_1920x1080_AVC_AAC_SRT_TVB_YUE_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][TVB粵語]愛犬訊號 / Dog Signal - 09 [粵語+內封繁體中文字幕][WEB 1920x1080 AVC AAC SRT TVB YUE CHT]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690360_A_Doraemon1979_1998_3_7_1080P_WEB_A_MP4`() {
    kotlin.run {
    val r =
        parse("[哆啦字幕組][哆啦A夢大山版附映中篇Doraemon1979][1998.3.7][1080P][WEB][繁日雙語][哆啦A夢歸來了][数位修復版][MP4]")
    assertEquals("null", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690359_A_Doraemon1979_1998_3_7_1080P_WEB_A_MP4`() {
    kotlin.run {
    val r =
        parse("[哆啦字幕组][哆啦A梦大山版附映中篇 Doraemon1979][1998.3.7][1080P][WEB][简日双语][哆啦A梦归来了][数码修复版][MP4]")
    assertEquals("null", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690358_LoliHouse_Ameku_Takao_s_Detective_Karte___08_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 天久鹰央的推理病历表 / Ameku Takao's Detective Karte - 08 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690357_____Ore_dake_Level_Up_na_Ken_S2_20_1080p_JaVer_2025_1`() {
    kotlin.run {
    val r =
        parse("[猎户压制部] 我独自升级 第二季 -起于暗影- / Ore dake Level Up na Ken S2 [20] [1080p] [繁日内嵌] [JaVer] [2025年1月番]")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690356_____Ore_dake_Level_Up_na_Ken_S2_20_1080p_JaVer_2025_1`() {
    kotlin.run {
    val r =
        parse("[猎户压制部] 我独自升级 第二季 -起于暗影- / Ore dake Level Up na Ken S2 [20] [1080p] [简日内嵌] [JaVer] [2025年1月番]")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690355_Shangri_La_Frontier_S2_45_1080p_2024_10`() {
    kotlin.run {
    val r =
        parse("[猎户压制部] 香格里拉·开拓异境～粪作猎手挑战神作 第二季 / Shangri-La Frontier S2 [45] [1080p] [繁日内嵌] [2024年10月番]")
    assertEquals("45..45", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690354_Shangri_La_Frontier_S2_45_1080p_2024_10`() {
    kotlin.run {
    val r =
        parse("[猎户压制部] 香格里拉·开拓异境～粪作猎手挑战神作 第二季 / Shangri-La Frontier S2 [45] [1080p] [简日内嵌] [2024年10月番]")
    assertEquals("45..45", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690353_DBD_4K_2160P_HDR_1080P_BanG_Dream_21_Ave_Mujica_09_WEB_DL_AAC_MKV`() {
    kotlin.run {
    val r =
        parse("[DBD制作组][4K(2160P)+HDR+1080P][BanG Dream! Ave Mujica][09][WEB-DL][简日双语内嵌][AAC][MKV]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690352_ANi___08_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] 不幸職業【鑑定士】其實是最強的 - 08 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690351_LoliHouse_DanMachi_S5___14_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 在地下城寻求邂逅是否搞错了什么 第五季 / DanMachi S5 - 14 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("14..14", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690350_ANi_BanG_Dream_21_Ave_Mujica___09_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] BanG Dream! Ave Mujica - 09 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690349_S1_BanG_Dream_21_Ave_Mujica_09_2160p_Bilibili_Web_DL_HDR_HEVC_AAC`() {
    kotlin.run {
    val r =
        parse("[S1百综字幕组]BanG Dream! Ave Mujica [09][2160p][Bilibili][Web-DL][HDR][HEVC_AAC][简日双语]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690348_ANi_Dr_STONE_S04_Dr_STONE___08_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[ANi] Dr STONE S04 / Dr.STONE 新石紀 第四季 - 08 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690345_LoliHouse_Izure_Saikyou_no_Renkinjutsushi___09_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 迟早是最强的炼金术师？ / Izure Saikyou no Renkinjutsushi? - 09 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690344_148_WEB_MKV_2160P`() {
    kotlin.run {
    val r = parse("[神印王座][第148集][国语中字][WEB-MKV][2160P]")
    assertEquals("148..148", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690343_204_WEB_MKV_2160P`() {
    kotlin.run {
    val r = parse("[完美世界][第204集][国语中字][WEB-MKV][2160P]")
    assertEquals("204..204", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690341_The_Colors_Within_2024_1080p_AMZN_WEB_DL_H264_DDP5_1_QuickIO`() {
    kotlin.run {
    val r = parse("你的颜色.The.Colors.Within.2024.1080p.AMZN.WEB-DL.H264.DDP5.1-QuickIO")
    assertEquals("null", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690339_LoliHouse_Around_40_Otoko_no_Isekai_Tsuuhan___06_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 中年男的异世界网购生活 / Around 40 Otoko no Isekai Tsuuhan - 06 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690338_LoliHouse_Around_40_Otoko_no_Isekai_Tsuuhan___05_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 中年男的异世界网购生活 / Around 40 Otoko no Isekai Tsuuhan - 05 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690334_Hana_wa_Saku_Shura_no_Gotoku_07_1080p`() {
    kotlin.run {
    val r = parse("[桜都字幕組] 群花綻放、彷如修羅 / Hana wa Saku, Shura no Gotoku [07][1080p][繁體內嵌]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690333_Hana_wa_Saku_Shura_no_Gotoku_07_1080p`() {
    kotlin.run {
    val r = parse("[桜都字幕组] 群花绽放、彷如修罗 / Hana wa Saku, Shura no Gotoku [07][1080p][简体内嵌]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690332_Re___12_CR_WebRip_AI2160p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r = parse("[不当舔狗制作组] Re：从零开始的异世界生活 第三季 - 12 [CR WebRip AI2160p HEVC-10bit AAC][官方简繁内封字幕]")
    assertEquals("12..12", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690331_Hana_wa_Saku_Shura_no_Gotoku_07_1080p`() {
    kotlin.run {
    val r = parse("[桜都字幕组] 群花绽放、彷如修罗 / Hana wa Saku, Shura no Gotoku [07][1080p][简繁内封]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690330_Re___12_CR_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r = parse("[不当舔狗制作组] Re：从零开始的异世界生活 第三季 - 12 [CR WebRip 1080p HEVC-10bit AAC][官方简繁内封字幕]")
    assertEquals("12..12", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690328_MagicStar_2024_WEBDL_1080p_U_NEXT`() {
    kotlin.run {
    val r =
        parse("[MagicStar] 蜡笔小新 剧场版 我们的恐龙日记 / 映画クレヨンしんちゃん オラたちの恐竜日記 2024 [WEBDL] [1080p] [U-NEXT]【生】")
    assertEquals("S?", r.episodeRange.toString())
    assertEquals("JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690325_10_Chi_____Chi_Chikyuu_no_Undou_ni_Tsuite_20_1080p`() {
    kotlin.run {
    val r =
        parse("【喵萌奶茶屋】★10月新番★[Chi。-关于地球的运动- / Chi. Chikyuu no Undou ni Tsuite][20][1080p][简日双语][招募翻译]")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690324_ANi_Ameku_MD_Doctor_Detective___08_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[ANi] Ameku MD Doctor Detective / 天久鷹央的推理病歷表 - 08 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690323_ANi_Honey_Lemon_Soda___08_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[ANi] Honey Lemon Soda / 青春特調蜂蜜檸檬蘇打 - 08 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690322_Skymoon_Raws_Is_It_Wrong_to_Try_to_Pick_Up_Girls_in_a_Dungeon_V_Goddess_of_Fertility_Arc___14_ViuTV_WEB_DL_1080p_AVC_AAC`() {
    kotlin.run {
    val r =
        parse("[Skymoon-Raws] 在地下城尋求邂逅是否搞錯了什麼 第五季 / Is It Wrong to Try to Pick Up Girls in a Dungeon? V: Goddess of Fertility Arc - 14 [ViuTV][WEB-DL][1080p][AVC AAC]")
    assertEquals("14..14", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690321_Skymoon_Raws_Re_Re_Zero_kara_Hajimeru_Isekai_Seikatsu_3rd_Season___12_ViuTV_WEB_DL_1080p_AVC_AAC`() {
    kotlin.run {
    val r =
        parse("[Skymoon-Raws] Re：從零開始的異世界生活 第三季 / Re：Zero kara Hajimeru Isekai Seikatsu 3rd Season - 12 [ViuTV][WEB-DL][1080p][AVC AAC]")
    assertEquals("12..12", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690320_______08V3_CR_WebRip_AI2160p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r = parse("[不当舔狗制作组] 我独自升级 第二季 -起于暗影- - 08V3 [CR WebRip AI2160p HEVC-10bit AAC][官方简繁内封字幕]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690319_The_Sea_Prince_and_the_Fire_Child_1981_Movie_1080p`() {
    kotlin.run {
    val r =
        parse("[冷番补完字幕组][天狼星的传说][シリウスの伝説][The Sea Prince and the Fire Child][1981]Movie[1080p][内封中英双语字幕]")
    assertEquals("S?", r.episodeRange.toString())
    assertEquals("ENG", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690318_ANi_Re___12_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] Re：從零開始的異世界生活 第三季 - 12 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("12..12", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690317_LoliHouse_Amagami_san_Chi_no_Enmusubi___20_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 结缘甘神神社 / Amagami-san Chi no Enmusubi - 20 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690316_LoliHouse_FAIRY_TAIL_01_25_WebRip_1080p_HEVC_10bit_AAC_Fin`() {
    kotlin.run {
    val r =
        parse("[动漫国字幕组&LoliHouse] FAIRY TAIL 魔导少年 / 妖精尾巴 百年任务 [01-25 精校合集][WebRip 1080p HEVC-10bit AAC][简繁内封字幕][Fin]")
    assertEquals("01..25", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690315_LoliHouse_Honey_Lemon_Soda___04_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[喵萌奶茶屋&LoliHouse] 蜂蜜柠檬苏打 / ハニーレモンソーダ / Honey Lemon Soda - 04 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("04..04", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690314_LoliHouse_Honey_Lemon_Soda___03_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[喵萌奶茶屋&LoliHouse] 蜂蜜柠檬苏打 / ハニーレモンソーダ / Honey Lemon Soda - 03 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("03..03", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690313_ANi___09_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] 遲早是最強的鍊金術師？ - 09 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690312_ANi___14_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] 在地下城尋求邂逅是否搞錯了什麼 第五季 - 14 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("14..14", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690311_YYQ_New_Doraemon_A_851_2025_02_22_AVC_1080P_GB_JP`() {
    kotlin.run {
    val r = parse("[夜莺家族&YYQ字幕组]New Doraemon 哆啦A梦新番[851][2025.02.22][AVC][1080P][GB_JP]")
    assertEquals("851..851", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690308_1_Magic_Maker_Isekai_Mahou_no_Tsukurikata_07_BIG5_MP4_1920X1080`() {
    kotlin.run {
    val r =
        parse("【幻櫻字幕組】【1月新番】【魔法製造者 ~異世界魔法的製作方法~ Magic Maker ~Isekai Mahou no Tsukurikata~】【07】【BIG5_MP4】【1920X1080】")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690307_1_Magic_Maker_Isekai_Mahou_no_Tsukurikata_07_GB_MP4_1920X1080`() {
    kotlin.run {
    val r =
        parse("【幻樱字幕组】【1月新番】【魔法制造者 ~异世界魔法的制作方法~ Magic Maker ~Isekai Mahou no Tsukurikata~】【07】【GB_MP4】【1920X1080】")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690306_DBD_Raws_You_re_Next_Boku_no_Hero_Academia_the_Movie_You_re_Next_1080P_BDRip_HEVC_10bit_FLAC_MKV_THE_MOVIE`() {
    kotlin.run {
    val r =
        parse("[DBD-Raws][我的英雄学院剧场版：You're Next/Boku no Hero Academia the Movie You're Next][正片+特典映像][1080P][BDRip][HEVC-10bit][FLAC][MKV](僕のヒーローアカデミア THE MOVIE ユアネクスト)")
    assertEquals("S?", r.episodeRange.toString())
    assertEquals("ENG", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690305_LoliHouse_ZENSHU___06_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r = parse("[喵萌奶茶屋&LoliHouse] 全修。 / ZENSHU - 06 [WebRip 1080p HEVC-10bit AAC][简繁日内封字幕]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690304___ef___a_tale_of_memories___02_BDRip_HEVC_10bit_1080p`() {
    kotlin.run {
    val r =
        parse("[❀拨雪寻春❀] 悠久之翼 - 前尘遗响 / ef - a tale of memories - 02 [BDRip][HEVC-10bit 1080p][繁日内嵌]")
    assertEquals("02..02", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690303___ef___a_tale_of_memories___02_BDRip_HEVC_10bit_1080p`() {
    kotlin.run {
    val r =
        parse("[❀拨雪寻春❀] 悠久之翼 - 前尘遗响 / ef - a tale of memories - 02 [BDRip][HEVC-10bit 1080p][简日内嵌]")
    assertEquals("02..02", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690302___ef___a_tale_of_memories___02_BDRip_HEVC_10bit_1080p`() {
    kotlin.run {
    val r =
        parse("[❀拨雪寻春❀] 悠久之翼 - 前尘遗响 / ef - a tale of memories - 02 [BDRip][HEVC-10bit 1080p][简繁日内封]")
    assertEquals("02..02", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690301___ef___a_tale_of_memories___01_BDRip_HEVC_10bit_1080p`() {
    kotlin.run {
    val r =
        parse("[❀拨雪寻春❀] 悠久之翼 - 前尘遗响 / ef - a tale of memories - 01 [BDRip][HEVC-10bit 1080p][繁日内嵌]")
    assertEquals("01..01", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690300___ef___a_tale_of_memories___01_BDRip_HEVC_10bit_1080p`() {
    kotlin.run {
    val r =
        parse("[❀拨雪寻春❀] 悠久之翼 - 前尘遗响 / ef - a tale of memories - 01 [BDRip][HEVC-10bit 1080p][简日内嵌]")
    assertEquals("01..01", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690299___ef___a_tale_of_memories___01_BDRip_HEVC_10bit_1080p`() {
    kotlin.run {
    val r =
        parse("[❀拨雪寻春❀] 悠久之翼 - 前尘遗响 / ef - a tale of memories - 01 [BDRip][HEVC-10bit 1080p][简繁日内封]")
    assertEquals("01..01", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690298___ef___a_tale_of_memories___00_BDRip_HEVC_10bit_1080p`() {
    kotlin.run {
    val r =
        parse("[❀拨雪寻春❀] 悠久之翼 - 前尘遗响 / ef - a tale of memories - 00 [BDRip][HEVC-10bit 1080p][繁日内嵌]")
    assertEquals("00..00", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690297___ef___a_tale_of_memories___00_BDRip_HEVC_10bit_1080p`() {
    kotlin.run {
    val r =
        parse("[❀拨雪寻春❀] 悠久之翼 - 前尘遗响 / ef - a tale of memories - 00 [BDRip][HEVC-10bit 1080p][简日内嵌]")
    assertEquals("00..00", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690296___ef___a_tale_of_memories___00_BDRip_HEVC_10bit_1080p`() {
    kotlin.run {
    val r =
        parse("[❀拨雪寻春❀] 悠久之翼 - 前尘遗响 / ef - a tale of memories - 00 [BDRip][HEVC-10bit 1080p][简繁日内封]")
    assertEquals("00..00", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690292_LevelX_Kankin_Kuiki_Level_X_07_CHS_1920x1080_AVC_AAC_mp4`() {
    kotlin.run {
    val r = parse("[黑白字幕组]监禁区域LevelX / Kankin Kuiki Level X [07][CHS](1920x1080 AVC AAC ).mp4")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690291_Botsuraku_Yotei_no_Kizoku_dakedo_Hima_Datta_kara_Mahou_wo_Kiwametemita_06_webRip_AVC_8bit_1080P_AAC`() {
    kotlin.run {
    val r =
        parse("[黑白字幕组]虽然我是注定没落的贵族，闲来无事只好来深究魔法 / Botsuraku Yotei no Kizoku dakedo, Hima Datta kara Mahou wo Kiwametemita [06] [webRip] [AVC-8bit 1080P AAC] [繁日内嵌]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690290_Botsuraku_Yotei_no_Kizoku_dakedo_Hima_Datta_kara_Mahou_wo_Kiwametemita_06_webRip_AVC_8bit_1080P_AAC`() {
    kotlin.run {
    val r =
        parse("[黑白字幕组]虽然我是注定没落的贵族，闲来无事只好来深究魔法 / Botsuraku Yotei no Kizoku dakedo, Hima Datta kara Mahou wo Kiwametemita [06] [webRip] [AVC-8bit 1080P AAC] [简日内嵌]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690288_1_Hana_wa_Saku_Shura_no_Gotoku_05_1080P_MP4_BIG5`() {
    kotlin.run {
    val r =
        parse("[愛戀字幕社][1月新番][群花綻放，彷如修羅][Hana wa Saku, Shura no Gotoku][05][1080P][MP4][BIG5][繁中]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690287_1_Hana_wa_Saku_Shura_no_Gotoku_05_1080P_MP4_GB`() {
    kotlin.run {
    val r = parse("[爱恋字幕社][1月新番][群花绽放，彷如修罗][Hana wa Saku, Shura no Gotoku][05][1080P][MP4][GB][简中]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690286_1_Hana_wa_Saku_Shura_no_Gotoku_04_1080P_MP4_BIG5`() {
    kotlin.run {
    val r =
        parse("[愛戀字幕社][1月新番][群花綻放，彷如修羅][Hana wa Saku, Shura no Gotoku][04][1080P][MP4][BIG5][繁中]")
    assertEquals("04..04", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690285_1_Hana_wa_Saku_Shura_no_Gotoku_04_1080P_MP4_GB`() {
    kotlin.run {
    val r = parse("[爱恋字幕社][1月新番][群花绽放，彷如修罗][Hana wa Saku, Shura no Gotoku][04][1080P][MP4][GB][简中]")
    assertEquals("04..04", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690284_LoliHouse_Kinomi_Master___09_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 最弱技能《果实大师》 ～关于能无限食用技能果实（吃了就会死）这件事～ / Kinomi Master - 09 [WebRip 1080p HEVC-10bit AAC][无字幕]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690274_LoliHouse_Ao_no_Hako_Blue_Box___19_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[喵萌奶茶屋&LoliHouse] 青箱 / Ao no Hako / Blue Box - 19 [WebRip 1080p HEVC-10bit AAC][简繁日内封字幕]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690273_LoliHouse_2_Watashi_no_Shiawase_na_Kekkon_S2___08_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[喵萌奶茶屋&LoliHouse] 我的幸福婚姻 2期 / Watashi no Shiawase na Kekkon S2 - 08 [WebRip 1080p HEVC-10bit AAC][简繁日内封字幕]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690272_SweetSub_LoliHouse_Momentary_Lily___08_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[SweetSub&LoliHouse] 刹那之花 / Momentary Lily - 08 [WebRip 1080p HEVC-10bit AAC][简日内封字幕]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690271_ANi_Hana_wa_Saku_Shura_no_Gotoku___08_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[ANi] Hana wa Saku Shura no Gotoku / 群花綻放、彷如修羅 - 08 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690270_98_WEB_MKV_2160P`() {
    kotlin.run {
    val r = parse("[遮天][第98集][国语中字][WEB-MKV][2160P]")
    assertEquals("98..98", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690269_LoliHouse_Kusuriya_no_Hitorigoto___30_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[喵萌奶茶屋&LoliHouse] 药师少女的独语 / 药屋少女的呢喃 / Kusuriya no Hitorigoto - 30 [WebRip 1080p HEVC-10bit AAC][简繁日内封字幕]")
    assertEquals("30..30", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690268_30_1080p`() {
    kotlin.run {
    val r = parse("[萌樱字幕组] 药屋少女的呢喃 [30][1080p][简繁日内封]")
    assertEquals("30..30", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690267_01_Zenshuu_ZENSHU_07_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[全修。 / Zenshuu. / ZENSHU][07][1080p][简日双语]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690266_01_Zenshuu_ZENSHU_06_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[全修。 / Zenshuu. / ZENSHU][06][1080p][繁日雙語]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690265_01_Zenshuu_ZENSHU_06_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[全修。 / Zenshuu. / ZENSHU][06][1080p][简日双语]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690264_01_2_Watashi_no_Shiawase_na_Kekkon_S2_08_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[我的幸福婚姻 2期 / Watashi no Shiawase na Kekkon S2][08][1080p][繁日雙語]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690263_01_2_Watashi_no_Shiawase_na_Kekkon_S2_08_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[我的幸福婚姻 2期 / Watashi no Shiawase na Kekkon S2][08][1080p][简日双语]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690262_01_Kusuriya_no_Hitorigoto_31_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[药师少女的独语 / 药屋少女的呢喃 / Kusuriya no Hitorigoto][31][1080p][简日双语]")
    assertEquals("31..31", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690261_01_Kusuriya_no_Hitorigoto_30_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[藥師少女的獨語 / 藥屋少女的呢喃 / Kusuriya no Hitorigoto][30][1080p][繁日雙語]")
    assertEquals("30..30", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690260_01_Kusuriya_no_Hitorigoto_30_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[药师少女的独语 / 药屋少女的呢喃 / Kusuriya no Hitorigoto][30][1080p][简日双语]")
    assertEquals("30..30", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690259_10_Ao_no_Hako_Blue_Box_19_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★10月新番★[青箱 / Ao no Hako / Blue Box][19][1080p][繁日雙語][招募翻譯]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690258_10_Ao_no_Hako_Blue_Box_19_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★10月新番★[青箱 / Ao no Hako / Blue Box][19][1080p][简日双语][招募翻译]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690257_Honey_Lemon_Soda_07_1080p`() {
    kotlin.run {
    val r = parse("[云光字幕组] 蜂蜜柠檬苏打水 Honey Lemon Soda [07][简体双语][1080p]招募翻译")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690256_LoliHouse_Mahoutsukai_no_Yakusoku___08_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 魔法使的约定 / Mahoutsukai no Yakusoku - 08 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690255_ANi_Amagamisan_Chi_no_Enmusubi___20_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[ANi] Amagamisan Chi no Enmusubi / 結緣甘神神社 - 20 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690254_Dr_STONE_Science_Future_03_1080p_2025_1`() {
    kotlin.run {
    val r = parse("[猎户压制部] 新石纪 第四季 科学与未来 / Dr.STONE：Science Future [03] [1080p] [繁日内嵌] [2025年1月番]")
    assertEquals("03..03", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690253_Dr_STONE_Science_Future_03_1080p_2025_1`() {
    kotlin.run {
    val r = parse("[猎户压制部] 石纪元 第四季 科学与未来 / Dr.STONE：Science Future [03] [1080p] [简日内嵌] [2025年1月番]")
    assertEquals("03..03", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690252_____Chi_Chikyuu_no_Undou_ni_Tsuite_22_1080p_2024_10`() {
    kotlin.run {
    val r =
        parse("[猎户手抄部] 地。-关于地球的运动- / Chi. Chikyuu no Undou ni Tsuite [22] [1080p] [繁日内嵌] [2024年10月番]")
    assertEquals("22..22", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690251_____Chi_Chikyuu_no_Undou_ni_Tsuite_22_1080p_2024_10`() {
    kotlin.run {
    val r =
        parse("[猎户手抄部] 地。-关于地球的运动- / Chi. Chikyuu no Undou ni Tsuite [22] [1080p] [简日内嵌] [2024年10月番]")
    assertEquals("22..22", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690250_Botsuraku_Yotei_no_Kizoku_dakedo_Hima_Datta_kara_Mahou_wo_Kiwametemita_05_webRip_AVC_8bit_1080P_AAC`() {
    kotlin.run {
    val r =
        parse("[黑白字幕组]虽然我是注定没落的贵族，闲来无事只好来深究魔法 / Botsuraku Yotei no Kizoku dakedo, Hima Datta kara Mahou wo Kiwametemita [05] [webRip] [AVC-8bit 1080P AAC] [繁日内嵌]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690249_Botsuraku_Yotei_no_Kizoku_dakedo_Hima_Datta_kara_Mahou_wo_Kiwametemita_05_webRip_AVC_8bit_1080P_AAC`() {
    kotlin.run {
    val r =
        parse("[黑白字幕组]虽然我是注定没落的贵族，闲来无事只好来深究魔法 / Botsuraku Yotei no Kizoku dakedo, Hima Datta kara Mahou wo Kiwametemita [05] [webRip] [AVC-8bit 1080P AAC] [简日内嵌]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690248_Yoru_wa_Neko_to_Issho_3_72_HEVC_10bit_1080P`() {
    kotlin.run {
    val r = parse("[织梦字幕组] 与猫共度的夜晚 Yoru wa Neko to Issho 3 [72] [HEVC-10bit 1080P] [简日双语] [特效字幕]")
    assertEquals("72..72", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690247_Yoru_wa_Neko_to_Issho_3_71_HEVC_10bit_1080P`() {
    kotlin.run {
    val r = parse("[织梦字幕组] 与猫共度的夜晚 Yoru wa Neko to Issho 3 [71] [HEVC-10bit 1080P] [简日双语] [特效字幕]")
    assertEquals("71..71", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690246_Yoru_wa_Neko_to_Issho_3_70_HEVC_10bit_1080P`() {
    kotlin.run {
    val r = parse("[织梦字幕组] 与猫共度的夜晚 Yoru wa Neko to Issho 3 [70] [HEVC-10bit 1080P] [简日双语] [特效字幕]")
    assertEquals("70..70", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690245_ANi___20_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] 妖怪學校的菜鳥老師 - 20 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690240_Yumezukuri_Yoru_wa_Neko_to_Issho_3_69_HEVC_10bit_1080P_CHS_JPN`() {
    kotlin.run {
    val r = parse("[Yumezukuri] Yoru wa Neko to Issho 3 [69] [HEVC-10bit 1080P] [CHS＆JPN]")
    assertEquals("69..69", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690239_Yoru_wa_Neko_to_Issho_3_68_HEVC_10bit_1080P`() {
    kotlin.run {
    val r = parse("[织梦字幕组] 与猫共度的夜晚 Yoru wa Neko to Issho 3 [68] [HEVC-10bit 1080P] [简日双语] [特效字幕]")
    assertEquals("68..68", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690236_Chibi_Maruko_chan_II_1468_2025_02_23_GB_JP_1080P_MP4`() {
    kotlin.run {
    val r =
        parse("[夜莺家族][樱桃小丸子第二期(Chibi Maruko-chan II)][1468]小丸子想模仿花轮&友藏想疼爱宏志[2025.02.23][GB_JP][1080P][MP4]")
    assertEquals("1468..1468", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690235_SweetSub_Momentary_Lily_08_WebRip_1080P_AVC_8bit`() {
    kotlin.run {
    val r = parse("[SweetSub][刹那之花][Momentary Lily][08][WebRip][1080P][AVC 8bit][简日内嵌]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690222_1_Kusuriya_no_Hitorigoto_31_1080P_MP4_GB_JP`() {
    kotlin.run {
    val r =
        parse("[漫猫字幕社&猫恋汉化组][1月新番][药屋少女的呢喃][Kusuriya no Hitorigoto][31][1080P][MP4][GB&JP][简日双语]")
    assertEquals("31..31", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690221_1_Kusuriya_no_Hitorigoto_31_1080P_MP4_BIG5_JP`() {
    kotlin.run {
    val r =
        parse("[漫貓字幕社&貓戀漢化組][1月新番][藥師少女的獨語][Kusuriya no Hitorigoto][31][1080P][MP4][BIG5&JP][繁日雙語]")
    assertEquals("31..31", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690216_Sub_BanG_Dream_21_Ave_Mujica_07_1080P_AVC_AAC_WebRip`() {
    kotlin.run {
    val r = parse("[霜庭雲花Sub][BanG Dream! Ave Mujica][07][1080P][AVC AAC][繁日雙語][WebRip]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690215_Sub_BanG_Dream_21_Ave_Mujica_07_1080P_AVC_AAC_WebRip`() {
    kotlin.run {
    val r = parse("[霜庭云花Sub][BanG Dream! Ave Mujica][07][1080P][AVC AAC][简日双语][WebRip]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690214_jibaketa_HOY_Pocket_Monsters_2023___36_WEB_1920x1080_AVC_AAC_PGS_SRT_iCABLE_YUE_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][HOY粵語]寶可夢 地平線 / Pocket Monsters (2023) - 36 [粵語+內封繁體中文字幕](WEB 1920x1080 AVC AAC PGS+SRT iCABLE YUE CHT)")
    assertEquals("36..36", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690200_LoliHouse_Botsuraku_Yotei_no_Kizoku_dakedo_Hima_Datta_kara_Mahou_wo_Kiwametemita___08_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 虽然我是注定没落的贵族，闲来无事只好来深究魔法 / Botsuraku Yotei no Kizoku dakedo, Hima Datta kara Mahou wo Kiwametemita - 08 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690199_Hana_wa_Saku_Shura_no_Gotoku_05_1080p_CHT`() {
    kotlin.run {
    val r = parse("【悠哈璃羽字幕社】[群花綻放 彷如修羅_Hana wa Saku,Shura no Gotoku][05][1080p][CHT]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690198_Hana_wa_Saku_Shura_no_Gotoku_05_1080p_CHS`() {
    kotlin.run {
    val r = parse("【悠哈璃羽字幕社】[群花绽放 彷如修罗_Hana wa Saku,Shura no Gotoku][05][1080p][CHS]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690197_Hana_wa_Saku_Shura_no_Gotoku_04_1080p_CHT`() {
    kotlin.run {
    val r = parse("【悠哈璃羽字幕社】[群花綻放 彷如修羅_Hana wa Saku,Shura no Gotoku][04][1080p][CHT]")
    assertEquals("04..04", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690196_Hana_wa_Saku_Shura_no_Gotoku_04_1080p_CHS`() {
    kotlin.run {
    val r = parse("【悠哈璃羽字幕社】[群花绽放 彷如修罗_Hana wa Saku,Shura no Gotoku][04][1080p][CHS]")
    assertEquals("04..04", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690193_LoliHouse_Kuroiwa_Medaka_ni_Watashi_no_Kawaii_ga_Tsuujinai___08_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 黑岩目高不把我的可爱放在眼里 / Kuroiwa Medaka ni Watashi no Kawaii ga Tsuujinai - 08 [WebRip 1080p HEVC-10bit AAC][无字幕]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690191_Zenshuu_08_webRip_AVC_8bit_1080P_AAC`() {
    kotlin.run {
    val r = parse("[黑白字幕组]全修。 / Zenshuu. [08] [webRip] [AVC-8bit 1080P AAC] [繁日内嵌]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690190_Zenshuu_08_webRip_AVC_8bit_1080P_AAC`() {
    kotlin.run {
    val r = parse("[黑白字幕组]全修。 / Zenshuu. [08] [webRip] [AVC-8bit 1080P AAC] [简日内嵌]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690189_VCB_Studio_TenSura_10_bit_1080p_HEVC_BDRip_S1_Reseed_OAD_Reseed_S2_Reseed_MOVIE_WEB_S3`() {
    kotlin.run {
    val r =
        parse("[VCB-Studio] 关于我转生变成史莱姆这档事 / Tensei Shitara Slime Datta Ken / 転生したらスライムだった件 10-bit 1080p HEVC BDRip [S1 Reseed + OAD Reseed + S2 Reseed + MOVIE + WEB + S3]")
    assertEquals("S1+OAD+S2+S?+S3", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690185_GM_Team_The_Eternal_Realm_2024_19_HEVC_GB_4K`() {
    kotlin.run {
    val r = parse("[GM-Team][国漫][长生界][The Eternal Realm][2024][19][HEVC][GB][4K]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690184_GM_Team_The_Eternal_Realm_2024_18_HEVC_GB_4K`() {
    kotlin.run {
    val r = parse("[GM-Team][国漫][长生界][The Eternal Realm][2024][18][HEVC][GB][4K]")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690183_07_08_GB_CN_HEVC_opus_1080p`() {
    kotlin.run {
    val r = parse("【极影字幕·毁片党】灰色：幻影扳机 第07-08集 GB_CN HEVC_opus 1080p")
    assertEquals("07..08", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690182_GM_Team_Swallowed_Star_2021_160_AVC_GB_1080P`() {
    kotlin.run {
    val r = parse("[GM-Team][国漫][吞噬星空][Swallowed Star][2021][160][AVC][GB][1080P]")
    assertEquals("160..160", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690181_GM_Team_Swallowed_Star_2021_159_AVC_GB_1080P`() {
    kotlin.run {
    val r = parse("[GM-Team][国漫][吞噬星空][Swallowed Star][2021][159[AVC][GB][1080P]")
    assertEquals("null", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690180_Sousou_no_Frieren___12_BDRip_HEVC_10bit_1080p`() {
    kotlin.run {
    val r =
        parse("[❀拨雪寻春❀] 葬送的芙莉莲 / 葬送のフリーレン / Sousou no Frieren - 12 [BDRip][HEVC-10bit 1080p][繁日内嵌]")
    assertEquals("12..12", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690179_Sousou_no_Frieren___12_BDRip_HEVC_10bit_1080p`() {
    kotlin.run {
    val r =
        parse("[❀拨雪寻春❀] 葬送的芙莉莲 / 葬送のフリーレン / Sousou no Frieren - 12 [BDRip][HEVC-10bit 1080p][简日内嵌]")
    assertEquals("12..12", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690178_Sousou_no_Frieren___12_BDRip_HEVC_10bit_1080p`() {
    kotlin.run {
    val r =
        parse("[❀拨雪寻春❀] 葬送的芙莉莲 / 葬送のフリーレン / Sousou no Frieren - 12 [BDRip][HEVC-10bit 1080p][简繁日内封]")
    assertEquals("12..12", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690176_Sentai_Red_Isekai_de_Boukensha_ni_Naru_07_1080p_WebRip_x264_AAC_CHS`() {
    kotlin.run {
    val r =
        parse("[蓝白汉化] 战队红战士在异世界当冒险者 / Sentai Red Isekai de Boukensha ni Naru [07][1080p][WebRip][x264_AAC][CHS]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690175_jibaketa_jibaketa_Crayon_Shin_chan_Mononoke_Ninja_Chinfuuden_BD_1920x1080_x264_AACx2_PGS_SRT_MOV_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][發行商粵語]蠟筆小新劇場版：幽靈忍者珍風傳 / Crayon Shin-chan Mononoke Ninja Chinfuuden [粵日雙語+內封繁體中文字幕] (BD 1920x1080 x264 AACx2 PGS+SRT MOV CHT)")
    assertEquals("S?", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690174_jibaketa_Dandadan___09_WEB_1920x1080_AVC_AACx2_SRT_MUSE_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成][代理商粵語]膽大黨 / Dandadan - 09 [粵日雙語+內封繁體中文字幕](WEB 1920x1080 AVC AACx2 SRT MUSE CHT)")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690173_Kusuriya_no_Hitorigoto_31_WebRip_HEVC_AAC`() {
    kotlin.run {
    val r =
        parse("[北宇治字幕组] 药屋少女的呢喃 / 药屋少女的独语 / Kusuriya no Hitorigoto [31][WebRip][HEVC_AAC][简繁日内封]")
    assertEquals("31..31", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690172_Kusuriya_no_Hitorigoto_31_WebRip_HEVC_AAC`() {
    kotlin.run {
    val r =
        parse("[北宇治字幕组] 药屋少女的呢喃 / 药屋少女的独语 / Kusuriya no Hitorigoto [31][WebRip][HEVC_AAC][简日内嵌]")
    assertEquals("31..31", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690171_Kusuriya_no_Hitorigoto_31_WebRip_HEVC_AAC`() {
    kotlin.run {
    val r =
        parse("[北宇治字幕组] 藥屋少女的呢喃 / 藥屋少女的獨語 / Kusuriya no Hitorigoto [31][WebRip][HEVC_AAC][繁日內嵌]")
    assertEquals("31..31", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690170_Arafou_Otoko_no_Isekai_Tsuuhan_Seikatsu_07_webRip_AVC_8bit_1080P_AAC`() {
    kotlin.run {
    val r =
        parse("[黑白字幕组]中年男的异世界网购生活 / Arafou Otoko no Isekai Tsuuhan Seikatsu [07] [webRip] [AVC-8bit 1080P AAC] [繁日内嵌]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690169_Arafou_Otoko_no_Isekai_Tsuuhan_Seikatsu_07_webRip_AVC_8bit_1080P_AAC`() {
    kotlin.run {
    val r =
        parse("[黑白字幕组]中年男的异世界网购生活 / Arafou Otoko no Isekai Tsuuhan Seikatsu [07] [webRip] [AVC-8bit 1080P AAC] [简日内嵌]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690168_SAKAMOTO_DAYS_05_1080p_2025_1`() {
    kotlin.run {
    val r = parse("[猎户手抄部] 坂本日常 / SAKAMOTO DAYS [05] [1080p] [繁日内嵌] [2025年1月番]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690167_SAKAMOTO_DAYS_05_1080p_2025_1`() {
    kotlin.run {
    val r = parse("[猎户手抄部] 坂本日常 / SAKAMOTO DAYS [05] [1080p] [简日内嵌] [2025年1月番]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690166_ANi_Promise_of_Wizard___08_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] Promise of Wizard / 魔法使的約定 - 08 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690165_ANi___09_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] 平凡上班族到異世界當上了四天王的故事 - 09 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690164_Dr_STONE_Science_Future_02_1080p_2025_1`() {
    kotlin.run {
    val r = parse("[猎户压制部] 新石纪 第四季 科学与未来 / Dr.STONE：Science Future [02] [1080p] [繁日内嵌] [2025年1月番]")
    assertEquals("02..02", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690163_Dr_STONE_Science_Future_02_1080p_2025_1`() {
    kotlin.run {
    val r = parse("[猎户压制部] 石纪元 第四季 科学与未来 / Dr.STONE：Science Future [02] [1080p] [简日内嵌] [2025年1月番]")
    assertEquals("02..02", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690162_ANi_I_Have_a_Crush_at_Work___08_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[ANi] I Have a Crush at Work / 這公司有我喜歡的人 - 08 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690161_1_07_HEVC_x265_10bit_1080p`() {
    kotlin.run {
    val r = parse("[云歌字幕组][1月新番][欢迎来到日本，精灵小姐][07][HEVC][x265 10bit][1080p][简日双语]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690160_Shakugan_no_Shana_I_06_BDRip_1080P_x264_AAC`() {
    kotlin.run {
    val r = parse("[晚街與燈][灼眼的夏娜 第一季_Shakugan no Shana I][06][BDRip_1080P_x264_AAC][繁日內嵌]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690159_Shakugan_no_Shana_I_06_BDRip_1080P_x264_AAC`() {
    kotlin.run {
    val r = parse("[晚街与灯][灼眼的夏娜 第一季_Shakugan no Shana I][06][BDRip_1080P_x264_AAC][简日内嵌]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690157_160_WEB_MKV_2160P`() {
    kotlin.run {
    val r = parse("[吞噬星空][第160集][国语中字][WEB-MKV][2160P]")
    assertEquals("160..160", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690154_Sousou_no_Frieren___11_BDRip_HEVC_10bit_1080p`() {
    kotlin.run {
    val r =
        parse("[❀拨雪寻春❀] 葬送的芙莉莲 / 葬送のフリーレン / Sousou no Frieren - 11 [BDRip][HEVC-10bit 1080p][繁日内嵌]")
    assertEquals("11..11", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690153_Sousou_no_Frieren___11_BDRip_HEVC_10bit_1080p`() {
    kotlin.run {
    val r =
        parse("[❀拨雪寻春❀] 葬送的芙莉莲 / 葬送のフリーレン / Sousou no Frieren - 11 [BDRip][HEVC-10bit 1080p][简日内嵌]")
    assertEquals("11..11", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690152_Sousou_no_Frieren___11_BDRip_HEVC_10bit_1080p`() {
    kotlin.run {
    val r =
        parse("[❀拨雪寻春❀] 葬送的芙莉莲 / 葬送のフリーレン / Sousou no Frieren - 11 [BDRip][HEVC-10bit 1080p][简繁日内封]")
    assertEquals("11..11", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690151_Sousou_no_Frieren___10_BDRip_HEVC_10bit_1080p`() {
    kotlin.run {
    val r =
        parse("[❀拨雪寻春❀] 葬送的芙莉莲 / 葬送のフリーレン / Sousou no Frieren - 10 [BDRip][HEVC-10bit 1080p][繁日内嵌]")
    assertEquals("10..10", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690150_Sousou_no_Frieren___10_BDRip_HEVC_10bit_1080p`() {
    kotlin.run {
    val r =
        parse("[❀拨雪寻春❀] 葬送的芙莉莲 / 葬送のフリーレン / Sousou no Frieren - 10 [BDRip][HEVC-10bit 1080p][简日内嵌]")
    assertEquals("10..10", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690149_Sousou_no_Frieren___10_BDRip_HEVC_10bit_1080p`() {
    kotlin.run {
    val r =
        parse("[❀拨雪寻春❀] 葬送的芙莉莲 / 葬送のフリーレン / Sousou no Frieren - 10 [BDRip][HEVC-10bit 1080p][简繁日内封]")
    assertEquals("10..10", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690147_VCB_Studio_Ayakashi_Triangle_10_bit_1080p_HEVC_BDRip_Reseed_Fin`() {
    kotlin.run {
    val r =
        parse("[喵萌奶茶屋&VCB-Studio] 妖幻三重奏 / Ayakashi Triangle / あやかしトライアングル 10-bit 1080p HEVC BDRip [Reseed Fin]")
    assertEquals("S?", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690146___19V2_CR_WebRip_AI2160p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r = parse("[不当舔狗制作组] 龙珠大魔 - 19V2 [CR WebRip AI2160p HEVC-10bit AAC][官方简繁内封字幕]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690143_ANi_Neko_ni_Tensei_Shita_Ojisan___20_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[ANi] Neko ni Tensei Shita Ojisan / 轉生成貓咪的大叔 - 20 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690141_GM_Team_Renegade_Immortal_2023_77_AVC_GB_1080P`() {
    kotlin.run {
    val r = parse("[GM-Team][国漫][仙逆][Renegade Immortal][2023][77][AVC][GB][1080P]")
    assertEquals("77..77", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690140_jibaketa_TVB_Let_s_Camp_21_Yuru_Camp_Season_3___05_BD_1920x1080_x264_AACx2_SRT_TVB_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&壓制][TVB粵語]Let's Camp!露營少女/搖曳露營/Yuru Camp Season 3 - 05 [粵日雙語+內封繁體中文字幕][BD 1920x1080 x264 AACx2 SRT TVB CHT]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690139_Yozakura_san_Chi_no_Daisakusen_01_27_1080P_MP4`() {
    kotlin.run {
    val r =
        parse("[愛戀字幕社&貓戀漢化組][夜櫻家的大作戰/夜櫻家大作戰][Yozakura-san Chi no Daisakusen][01-27][1080P][MP4][繁中]")
    assertEquals("01..27", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690138_Yozakura_san_Chi_no_Daisakusen_01_27_1080P_MP4`() {
    kotlin.run {
    val r =
        parse("[爱恋字幕社&猫恋汉化组][夜樱家的大作战/夜樱家大作战][Yozakura-san Chi no Daisakusen][01-27][1080P][MP4][简中]")
    assertEquals("01..27", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690137_10_Amagami_san_Chi_no_Enmusubi_15_17_1080P_MP4_BIG5`() {
    kotlin.run {
    val r =
        parse("[愛戀字幕社&貓戀漢化組][10月新番][緣結甘神家/結緣甘神神社][Amagami-san Chi no Enmusubi][15-17][1080P][MP4][BIG5][繁中]")
    assertEquals("15..17", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690136_10_Amagami_san_Chi_no_Enmusubi_15_17_1080P_MP4_GB`() {
    kotlin.run {
    val r =
        parse("[爱恋字幕社&猫恋汉化组][10月新番][缘结甘神家/结缘甘神神社][Amagami-san Chi no Enmusubi][15-17][1080P][MP4][GB][简中]")
    assertEquals("15..17", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690135_______08V2_CR_WebRip_AI2160p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r = parse("[不当舔狗制作组] 我独自升级 第二季 -起于暗影- - 08V2 [CR WebRip AI2160p HEVC-10bit AAC][官方简繁内封字幕]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690131_Shakugan_no_Shana_I_05_BDRip_1080P_x264_AAC`() {
    kotlin.run {
    val r = parse("[晚街與燈][灼眼的夏娜 第一季_Shakugan no Shana I][05][BDRip_1080P_x264_AAC][繁日內嵌]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690130_Shakugan_no_Shana_I_05_BDRip_1080P_x264_AAC`() {
    kotlin.run {
    val r = parse("[晚街与灯][灼眼的夏娜 第一季_Shakugan no Shana I][05][BDRip_1080P_x264_AAC][简日内嵌]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690129_Magic_Maker_Isekai_Mahou_no_Tsukurikata_07_webRip_AVC_8bit_1080P_AAC`() {
    kotlin.run {
    val r =
        parse("[黑白字幕组]魔术发明家 ~异世界魔法的制作方法~ / Magic Maker: Isekai Mahou no Tsukurikata [07] [webRip] [AVC-8bit 1080P AAC] [繁日内嵌]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690128_Magic_Maker_Isekai_Mahou_no_Tsukurikata_07_webRip_AVC_8bit_1080P_AAC`() {
    kotlin.run {
    val r =
        parse("[黑白字幕组]魔术发明家 ~异世界魔法的制作方法~ / Magic Maker: Isekai Mahou no Tsukurikata [07] [webRip] [AVC-8bit 1080P AAC] [简日内嵌]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690127_Umayuru_22_1080p`() {
    kotlin.run {
    val r = parse("【无戒汉化组】摇曳马娘 Umayuru [22][1080p][简中内嵌]")
    assertEquals("22..22", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690126_Skymoon_Raws_100_Hyakkano___19_ViuTV_WEB_RIP_1080p_AVC_AAC_CHT_SRT_MKV`() {
    kotlin.run {
    val r =
        parse("[Skymoon-Raws] 超超超超超喜歡你的100個女朋友 / Hyakkano - 19 [ViuTV][WEB-RIP][1080p][AVC AAC][CHT][SRT][MKV]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690125_080_082_1080P_MP4`() {
    kotlin.run {
    val r = parse("【枫叶字幕组】[宠物小精灵 / 宝可梦 地平线 烈空坐飞升][080-082][简体][1080P][MP4]")
    assertEquals("80..82", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690124_080_082_1080P_MP4`() {
    kotlin.run {
    val r = parse("【楓葉字幕組】[寵物小精靈 / 寶可夢 地平線 烈空坐飛升][080-082][繁體][1080P][MP4]")
    assertEquals("80..82", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690123_Grisaia_Phantom_Trigger_08_1080p`() {
    kotlin.run {
    val r = parse("[桜都字幕组] 灰色：幻影扳机 / Grisaia：Phantom Trigger [08][1080p][简繁内封]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690122_Grisaia_Phantom_Trigger_08_1080p`() {
    kotlin.run {
    val r = parse("[桜都字幕組] 灰色：幻影扳機 / Grisaia：Phantom Trigger [08][1080p][繁體內嵌]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690121_Grisaia_Phantom_Trigger_08_1080p`() {
    kotlin.run {
    val r = parse("[桜都字幕组] 灰色：幻影扳机 / Grisaia：Phantom Trigger [08][1080p][简体内嵌]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690120_Skymoon_Raws_Kisaki_Kyouiku_kara_Nigetai_Watashi___08_ViuTV_WEB_DL_1080p_AVC_AAC`() {
    kotlin.run {
    val r =
        parse("[Skymoon-Raws] 我想蹺掉太子妃培訓 / Kisaki Kyouiku kara Nigetai Watashi - 08 [ViuTV][WEB-DL][1080p][AVC AAC]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690119_Skymoon_Raws_Mashin_Souzouden_Wataru___07_ViuTV_WEB_DL_1080p_AVC_AAC`() {
    kotlin.run {
    val r =
        parse("[Skymoon-Raws] 魔神創造傳 / Mashin Souzouden Wataru - 07 [ViuTV][WEB-DL][1080p][AVC AAC]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690118_Skymoon_Raws_2_Shangri_La_Frontier_2nd_Season___20_ViuTV_WEB_DL_1080p_AVC_AAC`() {
    kotlin.run {
    val r =
        parse("[Skymoon-Raws] 香格里拉·開拓異境～糞作獵手挑戰神作～第2季 / Shangri-La Frontier 2nd Season - 20 [ViuTV][WEB-DL][1080p][AVC AAC]")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690117_ANi_Season_2___18_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] 金肉人 完美超人始祖篇 Season 2 - 18 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690115_____Rurouni_Kenshin_Meiji_Kenkaku_Romantan___Kyoto_Douran_43_1080p_2024_10`() {
    kotlin.run {
    val r =
        parse("[猎户手抄部] 神剑闯江湖 -明治剑客浪漫谭- 京都动乱 / Rurouni Kenshin：Meiji Kenkaku Romantan - Kyoto Douran [43] [1080p] [繁日内嵌] [2024年10月番]")
    assertEquals("43..43", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690114_____Rurouni_Kenshin_Meiji_Kenkaku_Romantan___Kyoto_Douran_43_1080p_2024_10`() {
    kotlin.run {
    val r =
        parse("[猎户手抄部] 浪客剑心 -明治剑客浪漫谭- 京都动乱 / Rurouni Kenshin: Meiji Kenkaku Romantan - Kyoto Douran [43] [1080p] [简日内嵌] [2024年10月番]")
    assertEquals("43..43", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690111_Class_no_Daikirai_na_Joshi_to_Kekkon_Suru_Koto_ni_Natta_08_1080p`() {
    kotlin.run {
    val r =
        parse("[桜都字幕组] 我和班上最讨厌的女生结婚了。 / Class no Daikirai na Joshi to Kekkon Suru Koto ni Natta. [08][1080p][简繁内封]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690110_ANi_100___19_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] 超超超超超喜歡你的 100 個女朋友 - 19 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690109_Class_no_Daikirai_na_Joshi_to_Kekkon_Suru_Koto_ni_Natta_08_1080p`() {
    kotlin.run {
    val r =
        parse("[桜都字幕組] 我和班上最討厭的女生結婚了。 / Class no Daikirai na Joshi to Kekkon Suru Koto ni Natta. [08][1080p][繁體內嵌]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690108_Class_no_Daikirai_na_Joshi_to_Kekkon_Suru_Koto_ni_Natta_08_1080p`() {
    kotlin.run {
    val r =
        parse("[桜都字幕组] 我和班上最讨厌的女生结婚了。 / Class no Daikirai na Joshi to Kekkon Suru Koto ni Natta. [08][1080p][简体内嵌]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690107_ANi___08_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] 我想蹺掉太子妃培訓 - 08 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690106_LoliHouse_Medalist___08_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r = parse("[喵萌奶茶屋&LoliHouse] 金牌得主 / Medalist - 08 [WebRip 1080p HEVC-10bit AAC][简繁日内封字幕]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690105_FSD_MIRAI_DAYS_06_1080P`() {
    kotlin.run {
    val r = parse("【FSD粉羽社】魔法使光之美少女！！～MIRAI DAYS～[06][1080P][简体中字]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690104_77_WEB_MKV_2160P`() {
    kotlin.run {
    val r = parse("[仙逆][第77集][国语中字][WEB-MKV][2160P]")
    assertEquals("77..77", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690103_jibaketa_ViuTV_Captain_Tsubasa_Season_2___Junior_Youth_Hen___21_WEB_1920x1080_AVC_AACx2_SRT_ViuTV_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][ViuTV粵語]隊長小翼第二季 青少年篇 / 世少篇 / Captain Tsubasa Season 2 - Junior Youth Hen - 21 (WEB 1920x1080 AVC AACx2 SRT ViuTV CHT)")
    assertEquals("21..21", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690101_Himitsu_no_Aipri_46`() {
    kotlin.run {
    val r = parse("[雪飄工作室][ひみつのアイプリ/Himitsu_no_Aipri/秘密的美妙公主][46][简繁外挂](检索:/美妙旋律/美妙天堂/美妙頻道/美妙魔法)")
    assertEquals("46..46", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public fun `690100_Himitsu_no_Aipri_720P_46`() {
    kotlin.run {
    val r =
        parse("[雪飄工作室][ひみつのアイプリ/Himitsu_no_Aipri/秘密的美妙公主][720P][46][繁](检索:/美妙旋律/美妙天堂/美妙頻道/美妙魔法)")
    assertEquals("46..46", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("720P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690096_10_Touhai_Ura_Rate_Mahjong_Touhai_Roku_19_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★10月新番★[冻牌 / Touhai: Ura Rate Mahjong Touhai Roku][19][1080p][简体][招募翻译]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690095_10_Touhai_Ura_Rate_Mahjong_Touhai_Roku_19_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★10月新番★[凍牌 / Touhai: Ura Rate Mahjong Touhai Roku][19][1080p][繁體][招募翻譯]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690094_01_Medalist_08_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[金牌得主 / Medalist][08][1080p][繁日雙語][招募翻譯]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690093_01_Medalist_08_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[金牌得主 / Medalist][08][1080p][简日双语][招募翻译]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690092_98_102_WEB_MKV_2160P`() {
    kotlin.run {
    val r = parse("[仙武传][第98-102集][国语中字][WEB-MKV][2160P]")
    assertEquals("98..102", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690091_18_19_WEB_MKV_2160P`() {
    kotlin.run {
    val r = parse("[牧神记][第18-19集][国语中字][WEB-MKV][2160P]")
    assertEquals("18..19", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690089_BanG_Dream_21_Ave_Mujica_06_WebRip_HEVC_AAC`() {
    kotlin.run {
    val r = parse("[北宇治字幕组] BanG Dream! Ave Mujica / 颂乐人偶 [06][WebRip][HEVC_AAC][简繁日内封]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690088_BanG_Dream_21_Ave_Mujica_06_WebRip_HEVC_AAC`() {
    kotlin.run {
    val r = parse("[北宇治字幕组] BanG Dream! Ave Mujica / 頌樂人偶 [06][WebRip][HEVC_AAC][繁日內嵌]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690087_BanG_Dream_21_Ave_Mujica_06_WebRip_HEVC_AAC`() {
    kotlin.run {
    val r = parse("[北宇治字幕组] BanG Dream! Ave Mujica / 颂乐人偶 [06][WebRip][HEVC_AAC][简日内嵌]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690086_ANi_Mashin_Creator_Wataru___07_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[ANi] Mashin Creator Wataru / 魔神創造傳 - 07 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690085_ANi_ToiletBound_Hanakokun_S02___07_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[ANi] ToiletBound Hanakokun S02 / 地縛少年花子君 第二季 - 07 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690084_ANi___20_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] 香格里拉・開拓異境～糞作獵手挑戰神作～ 第二季 - 20 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690083_LoliHouse_Ao_no_Exorcist_Yosuga_hen___08_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[豌豆字幕组&LoliHouse] 青之驱魔师 终夜篇 / Ao no Exorcist Yosuga-hen - 08 [WebRip 1080p HEVC-10bit AAC][简繁外挂字幕]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public fun `690082_LoliHouse_A_Aparida___07_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 离开A级队伍的我，和从前的弟子往迷宫深处迈进 / Aparida - 07 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690081_LoliHouse_NEET_Kunoichi___08_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 我与尼特女忍者的莫名同居生活 / NEET Kunoichi - 08 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690080_LoliHouse_Dragon_Ball_Daima___19_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 龙珠 大魔 / Dragon Ball Daima - 19 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690079_10_19_1080P_MP4`() {
    kotlin.run {
    val r = parse("【动漫国字幕组&澄空学园&华盟字幕社&千夏字幕组】★10月新番[冻牌][19][1080P][简体][MP4]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690078_10_19_1080P_MP4`() {
    kotlin.run {
    val r = parse("【動漫國字幕組&澄空學園&華盟字幕社&千夏字幕組】★10月新番[凍牌][19][1080P][繁體][MP4]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690077_You_and_Idol_Precure_1080p_04_Q`() {
    kotlin.run {
    val r =
        parse("[雪飘工作室][キミとアイドルプリキュア♪/You and Idol Precure♪/与你同为 偶像光之美少女♪][1080p][04][简繁外挂](检索:Q娃)")
    assertEquals("04..04", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public fun `690076_You_and_Idol_Precure_720p_04_Q`() {
    kotlin.run {
    val r =
        parse("[雪飄工作室][キミとアイドルプリキュア♪/You and Idol Precure♪/与你同为 偶像光之美少女♪][720p][04][繁體內嵌](檢索:Q娃)")
    assertEquals("04..04", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("720P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690075_You_and_Idol_Precure_720p_04_Q`() {
    kotlin.run {
    val r =
        parse("[雪飘工作室][キミとアイドルプリキュア♪/You and Idol Precure♪/与你同为 偶像光之美少女♪][720p][04][简体内嵌](检索:Q娃)")
    assertEquals("04..04", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("720P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690073_SweetSub_VCB_Studio_16bit_16bit_Sensation_Another_Layer_16bit_10_bit_1080p_HEVC_BDRip_Fin`() {
    kotlin.run {
    val r =
        parse("[SweetSub&VCB-Studio] 16位的辉煌 / 16bit Sensation Another Layer / 16bitセンセーション 10-bit 1080p HEVC BDRip [Fin]")
    assertEquals("S?", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690072_jibaketa_TVB_Shinkalion_Change_the_World___30_WEB_1920x1080_AVC_AAC_SRT_TVB_YUE_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][TVB粵語]新幹線戰士 改變世界 / 新幹線變形機器人 變革世代 / Shinkalion Change the World - 30 [粵語+內封繁體中文字幕][WEB 1920x1080 AVC AAC SRT TVB YUE CHT]")
    assertEquals("30..30", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690071_DBFC_019_1080P_MP4`() {
    kotlin.run {
    val r = parse("【DBFC字幕组】【龙珠 大魔】【019】【中日双语字幕】【1080P】【MP4】")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690070___20_NF_WebRip_AI2160p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r = parse("[不当舔狗制作组] 青之箱 - 20 [NF WebRip AI2160p HEVC-10bit AAC][官方简繁内封字幕]")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690069_Re_Re_Zero_kara_Hajimeru_Isekai_Seikatsu_61_WebRip_1080p_AVC_AAC`() {
    kotlin.run {
    val r =
        parse("【百冬練習組】【Re: 從零開始的異世界的生活_Re꞉ Zero kara Hajimeru Isekai Seikatsu】[61][WebRip 1080p AVC AAC][繁體]")
    assertEquals("61..61", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690068_Re_Re_Zero_kara_Hajimeru_Isekai_Seikatsu_61_WebRip_1080p_AVC_AAC`() {
    kotlin.run {
    val r =
        parse("【百冬练习组】【Re: 从零开始的异世界的生活_Re꞉ Zero kara Hajimeru Isekai Seikatsu】[61][WebRip 1080p AVC AAC][简体]")
    assertEquals("61..61", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690067___20_NF_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r = parse("[不当舔狗制作组] 青之箱 - 20 [NF WebRip 1080p HEVC-10bit AAC][官方简繁内封字幕]")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690066___19_NF_WebRip_AI2160p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r = parse("[不当舔狗制作组] 青之箱 - 19 [NF WebRip AI2160p HEVC-10bit AAC][官方简繁内封字幕]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690065___19_NF_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r = parse("[不当舔狗制作组] 青之箱 - 19 [NF WebRip 1080p HEVC-10bit AAC][官方简繁内封字幕]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690064_______08_CR_WebRip_AI2160p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r = parse("[不当舔狗制作组] 我独自升级 第二季 -起于暗影- - 08 [CR WebRip AI2160p HEVC-10bit AAC][官方简繁内封字幕]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690062_LoliHouse_____Ore_dake_Level_Up_na_Ken_Solo_Leveling___20_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 我独自升级 -起于暗影- / Ore dake Level Up na Ken / Solo Leveling - 20 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690044_LoliHouse_Douse_Koishite_Shimaunda___07_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 终究、与你相恋 / Douse, Koishite Shimaunda - 07 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690042_______08_CR_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r = parse("[不当舔狗制作组] 我独自升级 第二季 -起于暗影- - 08 [CR WebRip 1080p HEVC-10bit AAC][官方简繁内封字幕]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690041__21_21_MIRAI_DAYS_Maho_Girls_Precure_21_21__21_21_1080p_07_Q`() {
    kotlin.run {
    val r =
        parse("[雪飘工作室][魔法つかいプリキュア!!~MIRAI DAYS~/Maho_Girls_Precure!!/魔法少女 光之美少女!!~未来的日子~][1080p][07][简繁外挂](检索:Q娃)")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public fun `690040__21_21_MIRAI_DAYS_Maho_Girls_Precure_21_21__21_21_720p_07_Q`() {
    kotlin.run {
    val r =
        parse("[雪飘工作室][魔法つかいプリキュア!!~MIRAI DAYS~/Maho_Girls_Precure!!/魔法少女 光之美少女!!~未来的日子~][720p][07][简体内嵌](检索:Q娃)")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("720P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690039__21_21_MIRAI_DAYS_Maho_Girls_Precure_21_21__21_21_720p_07_Q`() {
    kotlin.run {
    val r =
        parse("[雪飄工作室][魔法つかいプリキュア!!~MIRAI DAYS~/Maho_Girls_Precure!!/魔法少女 光之美少女!!~未來的日子~][720p][07][繁體內嵌](檢索:Q娃)")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("720P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690038_Ao_no_Exorcist_Yosuga_Hen_08_1080P_MP4`() {
    kotlin.run {
    val r = parse("【豌豆字幕组】[青之驱魔师 终夜篇 / Ao no Exorcist _Yosuga Hen][08][简体][1080P][MP4]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690037_Ao_no_Exorcist_Yosuga_Hen_08_1080P_MP4`() {
    kotlin.run {
    val r = parse("【豌豆字幕組】[青之驅魔師 終夜篇 / Ao no Exorcist _Yosuga Hen][08][繁體][1080P][MP4]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690036_TensoRaws_BanG_Dream_21_Ave_Mujica_08_WEBRip_2160p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r = parse("[TensoRaws] BanG Dream! Ave Mujica [08] [WEBRip 2160p HEVC-10bit AAC]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690035_1154_WEBRIP_MKV_1080P`() {
    kotlin.run {
    val r = parse("[银色子弹字幕组][名侦探柯南][第1154集 屋久岛的山姬（后篇）][WEBRIP][简繁日多语MKV][1080P]")
    assertEquals("1154..1154", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690034_1154_WEBRIP_MP4_1080P`() {
    kotlin.run {
    val r = parse("[银色子弹字幕组][名侦探柯南][第1154集 屋久島的山姬（後篇）][WEBRIP][繁日雙語MP4][1080P]")
    assertEquals("1154..1154", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690033_1154_WEBRIP_MP4_1080P`() {
    kotlin.run {
    val r = parse("[银色子弹字幕组][名侦探柯南][第1154集 屋久岛的山姬（后篇）][WEBRIP][简日双语MP4][1080P]")
    assertEquals("1154..1154", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690032_LoliHouse_S_Beheneko___09_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] S级怪兽《贝希摩斯》被误认成小猫，成为精灵女孩的骑士（宠物）一起生活 / Beheneko - 09 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690031_ANi___08_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] 在沖繩喜歡上的女孩方言講得太過令人困擾 - 08 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690030_ANi_Blue_Exorcist___08_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] Blue Exorcist / 青之驅魔師 終夜篇 - 08 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690029___07_CR_WebRip_AI2160p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r = parse("[不当舔狗制作组] 药屋少女的呢喃 第二季 - 07 [CR WebRip AI2160p HEVC-10bit AAC][官方简繁内封字幕]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690028_Skymoon_Raws_____Ore_dake_Level_Up_na_Ken_Season_2__08_ViuTV_WEB_DL_1080p_AVC_AAC`() {
    kotlin.run {
    val r =
        parse("[Skymoon-Raws] 我獨自升級 第二季 -起於闇影- / Ore dake Level Up na Ken Season 2- 08 [ViuTV][WEB-DL][1080p][AVC AAC]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690027_ANi_A___07_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] 離開 A 級隊伍的我，和從前的弟子往迷宮深處邁進 - 07 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690026_ANi___08_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] 我與尼特女忍者的莫名同居生活 - 08 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690025_ANi_Solo_Leveling_S02___20_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[ANi] Solo Leveling S02 / 我獨自升級 第二季 －起於闇影－ - 20 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690024_Babanba_Banban_Vampire___07_NetFlix_1920x1080_AVC_AAC_MKV`() {
    kotlin.run {
    val r = parse("[天月搬運組] 吸吸吸吸吸血鬼 / Babanba Banban Vampire - 07 (NetFlix 1920x1080 AVC AAC MKV)")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690023_SAKAMOTO_DAYS_S01E08_Sakamotos_vs_the_Lab_1080p_NF_WEB_DL_DDP5_1_H_264_MULTi_VARYG_Multi_Audio_Multi_Subs`() {
    kotlin.run {
    val r =
        parse("SAKAMOTO DAYS S01E08 Sakamotos vs the Lab 1080p NF WEB-DL DDP5.1 H 264 MULTi-VARYG (Multi-Audio, Multi-Subs)")
    assertEquals("264..264", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690022_Strange_Raw_SAKAMOTO_DAYS_S01_07_Bilibili_WEB_DL_1080P_AVC_8Bits_AAC_2_0`() {
    kotlin.run {
    val r =
        parse("[Strange-Raw] 坂本日常 第一季 / SAKAMOTO DAYS S01 [07] [Bilibili] [WEB-DL] [1080P AVC-8Bits AAC 2.0] [简日内嵌字幕]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690021_ANi_UniteUp_21__Uni_Birth____07_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] UniteUp! 眾星齊聚 -Uni：Birth- - 07 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690020___07_CR_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r = parse("[不当舔狗制作组] 药屋少女的呢喃 第二季 - 07 [CR WebRip 1080p HEVC-10bit AAC][官方简繁内封字幕]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690019_Kuroiwa_Medaka_ni_Watashi_no_Kawaii_ga_Tsuujinai_07_webRip_AVC_8bit_1080P_AAC`() {
    kotlin.run {
    val r =
        parse("[黑白字幕组]我的可爱对黑岩目高不管用 / Kuroiwa Medaka ni Watashi no Kawaii ga Tsuujinai [07] [webRip] [AVC-8bit 1080P AAC] [繁日内嵌]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690018_Kuroiwa_Medaka_ni_Watashi_no_Kawaii_ga_Tsuujinai_07_webRip_AVC_8bit_1080P_AAC`() {
    kotlin.run {
    val r =
        parse("[黑白字幕组]我的可爱对黑岩目高不管用 / Kuroiwa Medaka ni Watashi no Kawaii ga Tsuujinai [07] [webRip] [AVC-8bit 1080P AAC] [简日内嵌]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690013_Arafou_Otoko_no_Isekai_Tsuuhan_Seikatsu_06_webRip_AVC_8bit_1080P_AAC`() {
    kotlin.run {
    val r =
        parse("[黑白字幕组]中年男的异世界网购生活 / Arafou Otoko no Isekai Tsuuhan Seikatsu [06] [webRip] [AVC-8bit 1080P AAC] [繁日内嵌]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690012_jibaketa_ViuTV_Wonderful_Precure_21___42_WEB_1920x1080_AVC_AAC_YUE`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][ViuTV粵語]美妙寵物 光之美少女！ / Wonderful Precure! - 42 [粵語+無字幕] (WEB 1920x1080 AVC AAC YUE)")
    assertEquals("42..42", r.episodeRange.toString())
    assertEquals("CHC", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690011_Arafou_Otoko_no_Isekai_Tsuuhan_Seikatsu_06_webRip_AVC_8bit_1080P_AAC`() {
    kotlin.run {
    val r =
        parse("[黑白字幕组]中年男的异世界网购生活 / Arafou Otoko no Isekai Tsuuhan Seikatsu [06] [webRip] [AVC-8bit 1080P AAC] [简日内嵌]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `690010_Skymoon_Raws_S_Neko_to_Machigawarete_Elf_Musume_no_Pet_toshite_Kurashitemasu___09_ViuTV_WEB_DL_1080p_AVC_AAC`() {
    kotlin.run {
    val r =
        parse("[Skymoon-Raws] S級怪獸《貝希摩斯》被誤認成小貓，成為精靈女孩的騎士（寵物）一起生活 / Neko to Machigawarete Elf Musume no Pet toshite Kurashitemasu - 09 [ViuTV][WEB-DL][1080p][AVC AAC]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690009_BanG_Dream_21_Ave_Mujica_2025_WEB_07_1080p`() {
    kotlin.run {
    val r = parse("[冷番补完字幕组][BanG Dream! Ave Mujica][2025][WEB][07][1080p][内封简繁中字]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690008_Dr_STONE_Science_Future_01_1080p_2025_1`() {
    kotlin.run {
    val r = parse("[猎户压制部] 新石纪 第四季 科学与未来 / Dr.STONE：Science Future [01] [1080p] [繁日内嵌] [2025年1月番]")
    assertEquals("01..01", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690007_Dr_STONE_Science_Future_01_1080p_2025_1`() {
    kotlin.run {
    val r = parse("[猎户压制部] 石纪元 第四季 科学与未来 / Dr.STONE：Science Future [01] [1080p] [简日内嵌] [2025年1月番]")
    assertEquals("01..01", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690006___19_CR_WebRip_AI2160p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r = parse("[不当舔狗制作组] 龙珠大魔 - 19 [CR WebRip AI2160p HEVC-10bit AAC][官方简繁内封字幕]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690005_SAKAMOTO_DAYS_04_1080p_2025_1`() {
    kotlin.run {
    val r = parse("[猎户手抄部] 坂本日常 / SAKAMOTO DAYS [04] [1080p] [繁日内嵌] [2025年1月番]")
    assertEquals("04..04", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690004_ANi_S___09_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[ANi] S 級怪獸《貝希摩斯》被誤認成小貓，成為精靈女孩的騎士（寵物）一起生活 - 09 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `690003_SAKAMOTO_DAYS_04_1080p_2025_1`() {
    kotlin.run {
    val r = parse("[猎户手抄部] 坂本日常 / SAKAMOTO DAYS [04] [1080p] [简日内嵌] [2025年1月番]")
    assertEquals("04..04", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690002___19_CR_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r = parse("[不当舔狗制作组] 龙珠大魔 - 19 [CR WebRip 1080p HEVC-10bit AAC][官方简繁内封字幕]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `690001_Okinawa_de_Suki_ni_Natta_Ko_ga_Hougen_Sugite_Tsura_Sugiru_07_1080p`() {
    kotlin.run {
    val r =
        parse("[桜都字幕組] 在沖繩喜歡上的女孩方言講得太過令人困擾 / Okinawa de Suki ni Natta Ko ga Hougen Sugite Tsura Sugiru [07][1080p][繁日內嵌]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `690000_Okinawa_de_Suki_ni_Natta_Ko_ga_Hougen_Sugite_Tsura_Sugiru_07_1080p`() {
    kotlin.run {
    val r =
        parse("[桜都字幕组] 喜欢的冲绳妹说方言 / Okinawa de Suki ni Natta Ko ga Hougen Sugite Tsura Sugiru [07][1080p][简日内嵌]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689999_Okinawa_de_Suki_ni_Natta_Ko_ga_Hougen_Sugite_Tsura_Sugiru_07_1080p`() {
    kotlin.run {
    val r =
        parse("[桜都字幕组] 喜欢的冲绳妹说方言 / Okinawa de Suki ni Natta Ko ga Hougen Sugite Tsura Sugiru [07][1080p][简繁日内封]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689997_LoliHouse_Kurakon___08_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 我和班上最讨厌的女生结婚了。 / クラスの大嫌いな女子と結婚することになった。 / Kurakon - 08 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689995_WBX_Wonderful_Precure_1080P_Q`() {
    kotlin.run {
    val r =
        parse("[雪飄工作室 & WBX][汪分精彩光之美少女！/Wonderful_Precure！/わんだふるぷりきゅあ！][大電影！心動不已 在遊戲的世界大冒險][1080P][繁](检索:Q娃/プリキュア)")
    assertEquals("null", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689994_LoliHouse_Akuyaku_Reijou_Tensei_Ojisan___07_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 大叔转生恶役大小姐 / Akuyaku Reijou Tensei Ojisan - 07 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689993_LoliHouse_Fuguushoku_Kanteishi_ga_Jitsu_wa_Saikyou_Datta___07_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 不幸职业【鉴定士】实则最强 / Fuguushoku 'Kanteishi' ga Jitsu wa Saikyou Datta - 07 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689992_LoliHouse_2_Watashi_no_Shiawase_na_Kekkon_S2___07_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[喵萌奶茶屋&LoliHouse] 我的幸福婚姻 2期 / Watashi no Shiawase na Kekkon S2 - 07 [WebRip 1080p HEVC-10bit AAC][简繁日内封字幕]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689991_135_WEB_MKV_2160P`() {
    kotlin.run {
    val r = parse("[斗破苍穹年番][第135集][国语中字][WEB-MKV][2160P]")
    assertEquals("135..135", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689989_WBX_Wonderful_Precure_720P_Q`() {
    kotlin.run {
    val r =
        parse("[雪飄工作室 & WBX][汪分精彩光之美少女！/Wonderful_Precure！/わんだふるぷりきゅあ！][大电影！·心动不已 在游戏的世界大冒险][720P][简](检索:Q娃/プリキュア)")
    assertEquals("S?", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("720P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689988_ANi_Blue_Miburo___19_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] Blue Miburo / 青之壬生浪 - 19 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689987_Production_LoliHouse_BanG_Dream_21_Ave_Mujica___08_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[喵萌Production&LoliHouse] BanG Dream! Ave Mujica - 08 [WebRip 1080p HEVC-10bit AAC][简繁日内封字幕]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689986_Production_LoliHouse_BanG_Dream_21_Ave_Mujica___07_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[喵萌Production&LoliHouse] BanG Dream! Ave Mujica - 07 [WebRip 1080p HEVC-10bit AAC][简繁日内封字幕]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689985_LoliHouse_Ishura_S2___07_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[喵萌奶茶屋&LoliHouse] 异修罗 第二季 / Ishura S2 - 07 [WebRip 1080p HEVC-10bit AAC][简繁日内封字幕]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689984_01_2_Watashi_no_Shiawase_na_Kekkon_S2_07_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[我的幸福婚姻 2期 / Watashi no Shiawase na Kekkon S2][07][1080p][繁日雙語]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689983_01_2_Watashi_no_Shiawase_na_Kekkon_S2_07_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[我的幸福婚姻 2期 / Watashi no Shiawase na Kekkon S2][07][1080p][简日双语]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689982_Production_01_BanG_Dream_21_Ave_Mujica_08_1080p`() {
    kotlin.run {
    val r = parse("【喵萌Production】★01月新番★[BanG Dream! Ave Mujica][08][1080p][繁日雙語][招募翻譯]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689981_Production_01_BanG_Dream_21_Ave_Mujica_07_1080p`() {
    kotlin.run {
    val r = parse("【喵萌Production】★01月新番★[BanG Dream! Ave Mujica][07][1080p][繁日雙語][招募翻譯]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689980_LoliHouse_Hana_wa_Saku_Shura_no_Gotoku___07_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[喵萌奶茶屋&LoliHouse] 群花绽放，仿如修罗 / Hana wa Saku Shura no Gotoku - 07 [WebRip 1080p HEVC-10bit AAC][简繁日内封字幕]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689979_LoliHouse_Nihon_e_Youkoso_Elf_san___07_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 精灵来日 / 歡迎來到日本，妖精小姐。 / 日本へようこそエルフさん。 / Nihon e Youkoso Elf-san. - 07 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689976_Skymoon_Raws_Guild_no_Uketsukejou_desu_ga_Zangyou_wa_Iya_nanode_Boss_wo_Solo_Toubatsu_Shiyou_to_Omoimasu___07_ViuTV_WEB_DL_1080p_AVC_AAC`() {
    kotlin.run {
    val r =
        parse("[Skymoon-Raws] 雖然是公會的櫃檯小姐，但因為不想加班所以打算獨自討伐迷宮頭目 / Guild no Uketsukejou desu ga, Zangyou wa Iya nanode Boss wo Solo Toubatsu Shiyou to Omoimasu - 07 [ViuTV][WEB-DL][1080p][AVC AAC]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689974_GalaxyRailroad_888_GO_RUSH_21_21_Yu_Gi_Oh_21_GO_RUSH__21_21_145_720P_GB`() {
    kotlin.run {
    val r = parse("[GalaxyRailroad-888] 游戏王GO RUSH!! Yu-Gi-Oh! GO RUSH !! 145 720P [GB_简中]")
    assertEquals("145..145", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("720P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689973_Skymoon_Raws_Dragonball_DaiMa___19_ViuTV_WEB_DL_1080p_AVC_AAC`() {
    kotlin.run {
    val r = parse("[Skymoon-Raws] 龍珠大魔 / Dragonball DaiMa - 19 [ViuTV][WEB-DL][1080p][AVC AAC]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689972_131_WEB_MKV_2160P`() {
    kotlin.run {
    val r = parse("[凡人修仙传][第131集][国语中字][WEB-MKV][2160P]")
    assertEquals("131..131", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689967_07_GB_CN_HEVC_opus_1080p`() {
    kotlin.run {
    val r = parse("【极影字幕·毁片党】 这家公司里有我喜欢的人 第07集 GB_CN HEVC_opus 1080p")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689965_01_Tasokare_Hotel_08_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[黄昏旅店 / Tasokare Hotel][08][1080p][简日双语][招募翻译]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689964_ANi___08_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[搬運][ANi] 我和班上最討厭的女生結婚了。 - 08 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689963_Kusuriya_no_Hitorigoto_31_1080P_MP4`() {
    kotlin.run {
    val r = parse("【豌豆字幕组】[药屋少女的呢喃（药师少女的独语）/ Kusuriya no Hitorigoto][31][简体][1080P][MP4]")
    assertEquals("31..31", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689962_Kusuriya_no_Hitorigoto_31_1080P_MP4`() {
    kotlin.run {
    val r = parse("【豌豆字幕組】[藥屋少女的呢喃（藥師少女的獨語）/ Kusuriya no Hitorigoto][31][繁體][1080P][MP4]")
    assertEquals("31..31", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689961_BanG_Dream_21_Ave_Mujica___08_CR_WebRip_AI2160p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[不当舔狗制作组] BanG Dream! Ave Mujica - 08 [CR WebRip AI2160p HEVC-10bit AAC][官方简繁内封字幕]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689960_BanG_Dream_21_Ave_Mujica___08_CR_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[不当舔狗制作组] BanG Dream! Ave Mujica - 08 [CR WebRip 1080p HEVC-10bit AAC][官方简繁内封字幕]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689959_ANi___19_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] 凍牌~地下麻將鬥牌錄~ - 19 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689958_WOOO__21_WEBRIP_MP4_MP4_MKV_1080P`() {
    kotlin.run {
    val r =
        parse("[银色子弹字幕组][名侦探柯南][特别篇WOOO 迷侦探!?毛利小五郎&演员优木真央美的烦恼][WEBRIP][简日双语MP4/繁日雙語MP4/简繁日多语MKV][1080P]")
    assertEquals("null", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689957_59_WEBRIP_MP4_MP4_MKV_1080P`() {
    kotlin.run {
    val r = parse("[银色子弹字幕组][名侦探柯南][第59集 首次跑腿杀人事件][WEBRIP][简日双语MP4/繁日雙語MP4/简繁日多语MKV][1080P]")
    assertEquals("59..59", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689956_DBD_Raws_Puniru_wa_Kawaii_Slime_01_06TV_1080P_BDRip_HEVC_10bit_FLACx2_MKV`() {
    kotlin.run {
    val r =
        parse("[DBD-Raws][噗妮露是可爱史莱姆/Puniru wa Kawaii Slime][01-06TV][1080P][BDRip][HEVC-10bit][FLACx2][MKV](ぷにるはかわいいスライム)")
    assertEquals("01..06", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689955_10_DAIMA_Dragon_Ball_DAIMA_13v2_BIG5_MP4_1920X1080`() {
    kotlin.run {
    val r = parse("【幻櫻字幕組】【10月新番】【龍珠DAIMA Dragon Ball DAIMA】【13v2】【BIG5_MP4】【1920X1080】")
    assertEquals("13..13", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689954_10_DAIMA_Dragon_Ball_DAIMA_13v2_BIG5_MP4_1280X720`() {
    kotlin.run {
    val r = parse("【幻櫻字幕組】【10月新番】【龍珠DAIMA Dragon Ball DAIMA】【13v2】【BIG5_MP4】【1280X720】")
    assertEquals("13..13", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("720P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689953_ANi_Sorairo_Utility___08_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] Sorairo Utility / 一桿青空 - 08 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689952_Zenshuu_07_1080p`() {
    kotlin.run {
    val r = parse("[桜都字幕组] 全修。 / Zenshuu. [07][1080p][简繁内封]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689951_Zenshuu_07_1080p`() {
    kotlin.run {
    val r = parse("[桜都字幕組] 全修。 / Zenshuu. [07][1080p][繁體內嵌]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689950_Zenshuu_07_1080p`() {
    kotlin.run {
    val r = parse("[桜都字幕组] 全修。 / Zenshuu. [07][1080p][简体内嵌]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689949_LoliHouse_Grisaia_Phantom_Trigger___07_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[澄空学园&动漫国字幕组&LoliHouse] 灰色: 幻影扳机 / グリザイア: ファントムトリガー / Grisaia: Phantom Trigger - 07 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689948_ANi___07_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[ANi] 雖然是公會的櫃檯小姐，但因為不想加班所以打算獨自討伐迷宮頭目 - 07 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689947_LoliHouse_Grisaia_Phantom_Trigger___06_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[澄空学园&动漫国字幕组&LoliHouse] 灰色: 幻影扳机 / グリザイア: ファントムトリガー / Grisaia: Phantom Trigger - 06 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689946_LoliHouse_Grisaia_Phantom_Trigger___05_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[澄空学园&动漫国字幕组&LoliHouse] 灰色: 幻影扳机 / グリザイア: ファントムトリガー / Grisaia: Phantom Trigger - 05 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689945_01_Hana_wa_Saku_Shura_no_Gotoku_07_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[群花綻放，彷如修羅 / Hana wa Saku Shura no Gotoku][07][1080p][繁日雙語][招募翻譯]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689944_01_Hana_wa_Saku_Shura_no_Gotoku_07_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[群花绽放，彷如修罗 / Hana wa Saku Shura no Gotoku][07][1080p][简日双语][招募翻译]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689943_Kusuriya_no_Hitorigoto___31v2_NetFlix_1920x1080_AVC_AAC_MKV`() {
    kotlin.run {
    val r =
        parse("[天月搬運組] 藥師少女的獨語 (藥屋少女的呢喃) / Kusuriya no Hitorigoto - 31v2 (NetFlix 1920x1080 AVC AAC MKV)")
    assertEquals("31..31", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689942_Kusuriya_no_Hitorigoto___31_NetFlix_1920x1080_AVC_AAC_MKV`() {
    kotlin.run {
    val r =
        parse("[天月搬運組] 藥師少女的獨語 (藥屋少女的呢喃) / Kusuriya no Hitorigoto - 31 (NetFlix 1920x1080 AVC AAC MKV)")
    assertEquals("31..31", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689941_01_05_07_1080P_MP4`() {
    kotlin.run {
    val r = parse("【澄空学园&动漫国字幕组】★01月新番[灰色 : 幻影扳机][05-07][1080P][简体][MP4]")
    assertEquals("05..07", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689940_01_05_07_1080P_MP4`() {
    kotlin.run {
    val r = parse("【澄空學園&動漫國字幕組】★01月新番[灰色 : 幻影扳機][05-07][1080P][繁體][MP4]")
    assertEquals("05..07", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689939_Amor_Kusuriya_no_Hitorigoto_31_WEBDL_1080P_MKV`() {
    kotlin.run {
    val r = parse("[Amor压制组][药师少女的独语 藥師少女的獨語 Kusuriya no Hitorigoto][31][简繁内封][WEBDL][1080P][MKV]")
    assertEquals("31..31", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689938_____Ore_dake_Level_Up_na_Ken_S2_19_1080p_KoVer_2025_1`() {
    kotlin.run {
    val r =
        parse("[猎户压制部] 我独自升级 第二季 -起于暗影- / Ore dake Level Up na Ken S2 [19] [1080p] [繁日内嵌] [KoVer] [2025年1月番]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689937_____Ore_dake_Level_Up_na_Ken_S2_19_1080p_KoVer_2025_1`() {
    kotlin.run {
    val r =
        parse("[猎户压制部] 我独自升级 第二季 -起于暗影- / Ore dake Level Up na Ken S2 [19] [1080p] [简日内嵌] [KoVer] [2025年1月番]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689936_LoliHouse_____Rurouni_Kenshin_2023___43_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 浪客剑心 -明治剑客浪漫谭- 京都动乱 / Rurouni Kenshin (2023) - 43 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("43..43", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689935_4_12_15_WEB_MKV_2160P`() {
    kotlin.run {
    val r = parse("[镇魂街 第4季][第12-15集][国语中字][WEB-MKV][2160P]")
    assertEquals("12..15", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689934_Unnamed_Memory_Act_2_19_1080p_2025_1`() {
    kotlin.run {
    val r = parse("[猎户手抄部] 无名记忆 第二季 / Unnamed Memory Act.2 [19] [1080p] [繁日内嵌] [2025年1月番]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689933_Unnamed_Memory_Act_2_19_1080p_2025_1`() {
    kotlin.run {
    val r = parse("[猎户手抄部] 无名记忆 第二季 / Unnamed Memory Act.2 [19] [1080p] [简日内嵌] [2025年1月番]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689932_ANi_Farmagia_FARMAGIA___07_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] Farmagia / FARMAGIA 魔農傳記 - 07 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689931_ANi_Tasokare_Hotel___08_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] Tasokare Hotel / 黃昏旅店 - 08 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689930_10_DAIMA_Dragon_Ball_DAIMA_18_BIG5_MP4_1920X1080`() {
    kotlin.run {
    val r = parse("【幻櫻字幕組】【10月新番】【龍珠DAIMA Dragon Ball DAIMA】【18】【BIG5_MP4】【1920X1080】")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689929_10_DAIMA_Dragon_Ball_DAIMA_18_GB_MP4_1920X1080`() {
    kotlin.run {
    val r = parse("【幻樱字幕组】【10月新番】【龙珠DAIMA Dragon Ball DAIMA】【18】【GB_MP4】【1920X1080】")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689928_Chibi_Maruko_chan_II_1467_2025_02_16_BIG5_1080P_MP4`() {
    kotlin.run {
    val r =
        parse("[丸子家族][櫻桃小丸子第二期(Chibi Maruko-chan II)][1467]藤木教永澤溜冰&小丸子想在危機中利用智慧[2025.02.16][BIG5][1080P][MP4]")
    assertEquals("1467..1467", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689927_10_DAIMA_Dragon_Ball_DAIMA_18_BIG5_MP4_1280X720`() {
    kotlin.run {
    val r = parse("【幻櫻字幕組】【10月新番】【龍珠DAIMA Dragon Ball DAIMA】【18】【BIG5_MP4】【1280X720】")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("720P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689926_10_DAIMA_Dragon_Ball_DAIMA_18_GB_MP4_1280X720`() {
    kotlin.run {
    val r = parse("【幻樱字幕组】【10月新番】【龙珠DAIMA Dragon Ball DAIMA】【18】【GB_MP4】【1280X720】")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("720P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689925_Chibi_Maruko_chan_II_1467_2025_02_16_GB_1080P_MP4`() {
    kotlin.run {
    val r =
        parse("[丸子家族][樱桃小丸子第二期(Chibi Maruko-chan II)][1467]藤木教永泽溜冰&小丸子想在危机中利用智慧[2025.02.16][GB][1080P][MP4]")
    assertEquals("1467..1467", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689924_04_BIG5_AVC_aac_816p`() {
    kotlin.run {
    val r = parse("【極影字幕·毀片黨】灰色：幻影扳機 第04集 BIG5 AVC_aac 816p")
    assertEquals("04..04", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689923_ANi_Welcome_to_Japan_Ms_Elf___07_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[ANi] Welcome to Japan Ms Elf / 歡迎來到日本，妖精小姐。 - 07 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689922_2_89_WEB_MKV_2160P`() {
    kotlin.run {
    val r = parse("[斗罗大陆2绝世唐门][第89集][国语中字][WEB-MKV][2160P]")
    assertEquals("89..89", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689920_LoliHouse_Fuguushoku_Kanteishi_ga_Jitsu_wa_Saikyou_Datta___06_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 不幸职业【鉴定士】实则最强 / Fuguushoku 'Kanteishi' ga Jitsu wa Saikyou Datta - 06 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689919_LoliHouse_Botsuraku_Yotei_no_Kizoku_dakedo_Hima_Datta_kara_Mahou_wo_Kiwametemita___07_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 虽然我是注定没落的贵族，闲来无事只好来深究魔法 / Botsuraku Yotei no Kizoku dakedo, Hima Datta kara Mahou wo Kiwametemita - 07 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689918_LoliHouse_Act_2_Act_2_Unnamed_Memory_Act_2___19_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[喵萌奶茶屋&LoliHouse] 无名记忆 Act.2 / 無名記憶 Act.2 / Unnamed Memory Act.2 - 19 [WebRip 1080p HEVC-10bit AAC][简繁日内封字幕]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689917_LoliHouse_SAKAMOTO_DAYS___07_WebRip_1080p_HEVC_10bit_AACx2`() {
    kotlin.run {
    val r =
        parse("[北宇治字幕组&LoliHouse] 坂本日常 / SAKAMOTO DAYS - 07 [WebRip 1080p HEVC-10bit AACx2][简繁日内封字幕]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689915_01_Ishura_S2_07_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[異修羅 第二季 / Ishura S2][07][1080p][繁日雙語][招募翻譯]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689914_jibaketa_TVB_Dog_Signal___08_WEB_1920x1080_AVC_AAC_SRT_TVB_YUE_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][TVB粵語]愛犬訊號 / Dog Signal - 08 [粵語+內封繁體中文字幕][WEB 1920x1080 AVC AAC SRT TVB YUE CHT]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689912_SweetSub_Momentary_Lily_08_WebRip_1080P_AVC_8bit`() {
    kotlin.run {
    val r = parse("[SweetSub][刹那之花][Momentary Lily][08][WebRip][1080P][AVC 8bit][简体内嵌]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689911_LoliHouse_Myth_of_Emotions_Sousei_no_Aquarion_Myth_of_Emotions___07_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 想星的大天使 Myth of Emotions / Sousei no Aquarion: Myth of Emotions - 07 [WebRip 1080p HEVC-10bit AAC][无字幕]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689910_SAKAMOTO_DAYS_07_WebRip_HEVC_AAC`() {
    kotlin.run {
    val r = parse("[北宇治字幕组] 坂本日常 / SAKAMOTO DAYS [07][WebRip][HEVC_AAC][简日內嵌]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689909_SAKAMOTO_DAYS_07_WebRip_HEVC_AAC`() {
    kotlin.run {
    val r = parse("[北宇治字幕组] 坂本日常 / SAKAMOTO DAYS [07][WebRip][HEVC_AAC][繁日內嵌]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689908_SAKAMOTO_DAYS_06_WebRip_HEVC_AAC`() {
    kotlin.run {
    val r = parse("[北宇治字幕组] 坂本日常 / SAKAMOTO DAYS [07][WebRip][HEVC_AAC][简繁日內封]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689907_ANi__21_21_Divinez_DELUXE___06_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[ANi] 卡片戰鬥!! 先導者 Divinez 第三季「DELUXE篇」 - 06 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689904_Rev_DBD_Raws_Magia_Record_Mahou_Shoujo_Madoka_Magica_Gaiden_Final___Asaki_Yume_no_Akatsuki_01_04TV_1080P_BDRip_HEVC_10bit_FLAC_MKV_Final_Season____`() {
    kotlin.run {
    val r =
        parse("[Rev][DBD-Raws][魔法纪录 魔法少女小圆外传 最终季 浅梦的黎明/Magia Record Mahou Shoujo Madoka Magica Gaiden Final - Asaki Yume no Akatsuki][01-04TV全集][1080P][BDRip][HEVC-10bit][简繁外挂][FLAC][MKV](マギアレコード 魔法少女まどか☆マギカ外伝 Final Season -浅き夢の暁-)")
    assertEquals("01..04", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689902_DBD_Raws_Magia_Record_Mahou_Shoujo_Madoka_Magica_Gaiden_S2___Kakusei_Zen_ya_01_08TV_1080P_BDRip_HEVC_10bit_FLAC_MKV_2nd_SEASON____`() {
    kotlin.run {
    val r =
        parse("[DBD-Raws][魔法纪录 魔法少女小圆外传 第二季 觉醒前夜/Magia Record Mahou Shoujo Madoka Magica Gaiden S2 - Kakusei Zen`ya][01-08TV全集+特典][1080P][BDRip][HEVC-10bit][简繁外挂][FLAC][MKV](マギアレコード 魔法少女まどか☆マギカ外伝 2nd SEASON -覚醒前夜-)")
    assertEquals("01..08", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public fun `689898_Dr_STONE_SCIENCE_FUTURE_02_CR_WebRip_1080p_HEVC_AAC_S4`() {
    kotlin.run {
    val r =
        parse("[芝士动物朋友] 石纪元 科学与未来 / Dr.STONE SCIENCE FUTURE [02][CR-WebRip][1080p][HEVC+AAC][简繁内封]（新石纪 第四季 S4）")
    assertEquals("02..02", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689895_Ao_no_Hako_21_WEBrip_1080P`() {
    kotlin.run {
    val r = parse("【今晚月色真美】[青之箱 / アオのハコ / Ao no Hako][21][WEBrip][1080P][简日双语]")
    assertEquals("21..21", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689894_Production_01_BanG_Dream_21_Ave_Mujica_08_1080p`() {
    kotlin.run {
    val r = parse("【喵萌Production】★01月新番★[BanG Dream! Ave Mujica][08][1080p][简日双语][招募翻译]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689893_01_Act_2_Unnamed_Memory_Act_2_19_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[無名記憶 Act.2 / Unnamed Memory Act.2][19][1080p][繁日雙語][招募翻譯時軸]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689892_SweetSub_LoliHouse_Momentary_Lily___07_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[SweetSub&LoliHouse] 刹那之花 / Momentary Lily - 07 [WebRip 1080p HEVC-10bit AAC][简日内封字幕]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689891_ANi_Rurouni_Kenshin___19_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[ANi] Rurouni Kenshin / 神劍闖江湖 ―明治劍客浪漫譚― 京都動亂 - 19 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689890_Skymoon_Raws_Akuyaku_Reijou_Tensei_Ojisan___07_ViuTV_WEB_DL_1080p_AVC_AAC`() {
    kotlin.run {
    val r =
        parse("[Skymoon-Raws] 中年大叔轉生反派千金 / Akuyaku Reijou Tensei Ojisan - 07 [ViuTV][WEB-DL][1080p][AVC AAC]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689889_01_Ishura_S2_07_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[异修罗 第二季 / Ishura S2][07][1080p][简日双语][招募翻译]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689888_ANi_Anyway_Im_Falling_in_Love_with_You___07_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[ANi] Anyway Im Falling in Love with You / 終究，與你相戀。 - 07 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689887_ANi_TRILLION_GAME___20_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] TRILLION GAME / 一兆＄遊戲 - 20 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689886_203_WEB_MKV_2160P`() {
    kotlin.run {
    val r = parse("[完美世界][第203集][国语中字][WEB-MKV][2160P]")
    assertEquals("203..203", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689885_ANi_Momentary_Lily_MOMENTARY_LILY___08_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[ANi] Momentary Lily / MOMENTARY LILY 剎那之花 - 08 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689884___02_NF_WebRip_AI2160p_HEVC_10bit_E_AC_3`() {
    kotlin.run {
    val r = parse("[不当舔狗制作组] 坂本日常 - 02 [NF WebRip AI2160p HEVC-10bit E-AC-3][日英双语音频 官方简繁内封字幕]")
    assertEquals("02..02", r.episodeRange.toString())
    assertEquals("CHS, CHT, ENG, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id
        })
    assertEquals("4K", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689883_ANi___07_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] 中年大叔轉生反派千金 - 07 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689882___02_NF_WebRip_1080p_HEVC_10bit_E_AC_3`() {
    kotlin.run {
    val r = parse("[不当舔狗制作组] 坂本日常 - 02 [NF WebRip 1080p HEVC-10bit E-AC-3][日英双语音频 官方简繁内封字幕]")
    assertEquals("02..02", r.episodeRange.toString())
    assertEquals("CHS, CHT, ENG, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id
        })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689881_jibaketa_TVB_Dog_Signal___07_WEB_1920x1080_x264_AAC_SRT_TVB_YUE_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&二次壓制][TVB粵語]愛犬訊號 / Dog Signal - 07 [粵語+內封繁體中文字幕][WEB 1920x1080 x264 AAC SRT TVB YUE CHT]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689880_LoliHouse_Ameku_Takao_s_Detective_Karte___07_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 天久鹰央的推理病历表 / Ameku Takao's Detective Karte - 07 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689879_____Ore_dake_Level_Up_na_Ken_S2_19_1080p_JaVer_2025_1`() {
    kotlin.run {
    val r =
        parse("[猎户压制部] 我独自升级 第二季 -起于暗影- / Ore dake Level Up na Ken S2 [19] [1080p] [繁日内嵌] [JaVer] [2025年1月番]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689878_____Ore_dake_Level_Up_na_Ken_S2_19_1080p_JaVer_2025_1`() {
    kotlin.run {
    val r =
        parse("[猎户压制部] 我独自升级 第二季 -起于暗影- / Ore dake Level Up na Ken S2 [19] [1080p] [简日内嵌] [JaVer] [2025年1月番]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689877_ANi___07_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] 不幸職業【鑑定士】其實是最強的 - 07 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689876_DBD_4K_2160P_HDR_1080P_BanG_Dream_21_Ave_Mujica_08_WEB_DL_AAC_MKV`() {
    kotlin.run {
    val r =
        parse("[DBD制作组][4K(2160P)+HDR+1080P][BanG Dream! Ave Mujica][08][WEB-DL][简日双语内嵌][AAC][MKV]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689875_Shangri_La_Frontier_S2_44_1080p_2024_10`() {
    kotlin.run {
    val r =
        parse("[猎户压制部] 香格里拉·开拓异境～粪作猎手挑战神作 第二季 / Shangri-La Frontier S2 [44] [1080p] [繁日内嵌] [2024年10月番]")
    assertEquals("44..44", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689874_Shangri_La_Frontier_S2_44_1080p_2024_10`() {
    kotlin.run {
    val r =
        parse("[猎户压制部] 香格里拉·开拓异境～粪作猎手挑战神作 第二季 / Shangri-La Frontier S2 [44] [1080p] [简日内嵌] [2024年10月番]")
    assertEquals("44..44", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689871_S1_BanG_Dream_21_Ave_Mujica_08_2160p_Bilibili_Web_DL_HDR_HEVC_AAC`() {
    kotlin.run {
    val r =
        parse("[S1百综字幕组]BanG Dream! Ave Mujica [08][2160p][Bilibili][Web-DL][HDR][HEVC_AAC][简日双语]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689870_ANi_Dr_STONE_S04_Dr_STONE___07_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[ANi] Dr STONE S04 / Dr.STONE 新石紀 第四季 - 07 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689869_ANi_BanG_Dream_21_Ave_Mujica___08_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] BanG Dream! Ave Mujica - 08 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689868_DBD_Raws_Magia_Record_Mahou_Shoujo_Madoka_Magica_Gaiden_S1_01_13TV_1080P_BDRip_HEVC_10bit_FLAC_MKV_S1`() {
    kotlin.run {
    val r =
        parse("[DBD-Raws][魔法纪录 魔法少女小圆外传 第一季/Magia Record Mahou Shoujo Madoka Magica Gaiden S1][01-13TV全集][1080P][BDRip][HEVC-10bit][简繁外挂][FLAC][MKV](マギアレコード 魔法少女まどか☆マギカ外伝 S1)")
    assertEquals("01..13", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689867_LoliHouse_Sentai_Red_Isekai_de_Boukensha_ni_Naru___02_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[悠哈璃羽字幕社&LoliHouse] 战队红战士在异世界当冒险者 / Sentai Red Isekai de Boukensha ni Naru - 02 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("02..02", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689866_SweetSub_Momentary_Lily_07_WebRip_1080P_AVC_8bit`() {
    kotlin.run {
    val r = parse("[SweetSub][刹那之花][Momentary Lily][07][WebRip][1080P][AVC 8bit][简日内嵌]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689865_Re___11_CR_WebRip_AI2160p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r = parse("[不当舔狗制作组] Re：从零开始的异世界生活 第三季 - 11 [CR WebRip AI2160p HEVC-10bit AAC][官方简繁内封字幕]")
    assertEquals("11..11", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689859_LoliHouse_Izure_Saikyou_no_Renkinjutsushi___08_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 迟早是最强的炼金术师？ / Izure Saikyou no Renkinjutsushi? - 08 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689857_DBD_Raws_Gekijouban_Mahouka_Koukou_no_Rettousei_Hoshi_wo_Yobu_Shoujo_1080P_BDRip_HEVC_10bit_FLAC_DTS_MKV`() {
    kotlin.run {
    val r =
        parse("[DBD-Raws][剧场版 魔法科高中的劣等生 呼唤繁星的少女/Gekijouban Mahouka Koukou no Rettousei Hoshi wo Yobu Shoujo][正片+特典映像][1080P][BDRip][HEVC-10bit][简繁外挂][FLAC+DTS][MKV](劇場版 魔法科高校の劣等生 星を呼ぶ少女)")
    assertEquals("S?", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689856_DBD_Raws_Mahouka_Koukou_no_Rettousei_Tsuioku_Hen_1080P_BDRip_HEVC_10bit_FLAC_MKV`() {
    kotlin.run {
    val r =
        parse("[DBD-Raws][魔法科高中的劣等生 追忆篇/Mahouka Koukou no Rettousei Tsuioku Hen][正片+特典映像][1080P][BDRip][HEVC-10bit][简繁外挂][FLAC][MKV](魔法科高校の劣等生 追憶編)")
    assertEquals("S?", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689855_DBD_Raws_Mahouka_Koukou_no_Rettousei_Raihousha_Hen_S2_01_13TV_1080P_BDRip_HEVC_10bit_FLAC_MKV_S2`() {
    kotlin.run {
    val r =
        parse("[DBD-Raws][魔法科高中的劣等生 来访者篇(第二季)/Mahouka Koukou no Rettousei Raihousha Hen(S2)][01-13TV全集+特典映像][1080P][BDRip][HEVC-10bit][简繁外挂][FLAC][MKV](魔法科高校の劣等生 来訪者編(S2))")
    assertEquals("01..13", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public fun `689854_SweetSub_Shoushimin_Series_01_10_BDRip_1080P_AVC_8bit`() {
    kotlin.run {
    val r = parse("[SweetSub][小市民系列][Shoushimin Series][01-10 精校合集][BDRip][1080P][AVC 8bit][繁日雙語]")
    assertEquals("01..10", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689853_SweetSub_Shoushimin_Series_01_10_BDRip_1080P_AVC_8bit`() {
    kotlin.run {
    val r = parse("[SweetSub][小市民系列][Shoushimin Series][01-10 精校合集][BDRip][1080P][AVC 8bit][简日双语]")
    assertEquals("01..10", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689852_Babanba_Banban_Vampire_05_1080P_CHT`() {
    kotlin.run {
    val r = parse("【悠哈璃羽字幕社】[嘻嘻嘻嘻吸血鬼][Babanba Banban Vampire][05][1080P][CHT]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689851_Babanba_Banban_Vampire_05_1080P_CHS`() {
    kotlin.run {
    val r = parse("【悠哈璃羽字幕社】[嘻嘻嘻嘻吸血鬼][Babanba Banban Vampire][05][1080P][CHS]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689849_DBD_Raws_Mahouka_Koukou_no_Rettousei_S1_01_26TV_1080P_BDRip_HEVC_10bit_FLAC_MKV_S1`() {
    kotlin.run {
    val r =
        parse("[DBD-Raws][魔法科高中的劣等生 第一季/Mahouka Koukou no Rettousei S1][01-26TV全集+特典映像][1080P][BDRip][HEVC-10bit][简繁外挂][FLAC][MKV](魔法科高校の劣等生 S1)")
    assertEquals("01..26", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public fun `689848_Babanba_Banban_Vampire_04_1080P_CHT`() {
    kotlin.run {
    val r = parse("【悠哈璃羽字幕社】[嘻嘻嘻嘻吸血鬼][Babanba Banban Vampire][04][1080P][CHT]")
    assertEquals("04..04", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689847_Babanba_Banban_Vampire_04_1080P_CHS`() {
    kotlin.run {
    val r = parse("【悠哈璃羽字幕社】[嘻嘻嘻嘻吸血鬼][Babanba Banban Vampire][04][1080P][CHS]")
    assertEquals("04..04", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689845_VCB_Studio_SPYxFAMILY_10_bit_1080p_HEVC_BDRip_S1_S2_MOVIE_Fin`() {
    kotlin.run {
    val r =
        parse("[喵萌奶茶屋&VCB-Studio] 间谍过家家 / 间谍家家酒 / SPYxFAMILY 10-bit 1080p HEVC BDRip [S1-S2 + MOVIE Fin]")
    assertEquals("S1+S2+S?", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689842_Re___11_CR_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r = parse("[不当舔狗制作组] Re：从零开始的异世界生活 第三季 - 11 [CR WebRip 1080p HEVC-10bit AAC][官方简繁内封字幕]")
    assertEquals("11..11", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689841_01_Act_2_Unnamed_Memory_Act_2_19_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[无名记忆 Act.2 / Unnamed Memory Act.2][19][1080p][简日双语][招募翻译时轴]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689840_Skymoon_Raws_Re_Re_Zero_kara_Hajimeru_Isekai_Seikatsu_3rd_Season___11_ViuTV_WEB_DL_1080p_AVC_AAC`() {
    kotlin.run {
    val r =
        parse("[Skymoon-Raws] Re：從零開始的異世界生活 第三季 / Re：Zero kara Hajimeru Isekai Seikatsu 3rd Season - 11 [ViuTV][WEB-DL][1080p][AVC AAC]")
    assertEquals("11..11", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689839_ANi_Ameku_MD_Doctor_Detective___07_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[ANi] Ameku MD Doctor Detective / 天久鷹央的推理病歷表 - 07 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689838_Sentai_Red_Isekai_de_Boukensha_ni_Naru_02_x264_1080p_CHT`() {
    kotlin.run {
    val r =
        parse("【悠哈璃羽字幕社】[戰隊紅戰士在異世界當冒險者_Sentai Red Isekai de Boukensha ni Naru][02][x264 1080p][CHT]")
    assertEquals("02..02", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689837_Sentai_Red_Isekai_de_Boukensha_ni_Naru_02_x264_1080p_CHS`() {
    kotlin.run {
    val r =
        parse("【悠哈璃羽字幕社】[战队红战士在异世界当冒险者_Sentai Red Isekai de Boukensha ni Naru][02][x264 1080p][CHS]")
    assertEquals("02..02", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689836_ANi_Honey_Lemon_Soda___07_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[ANi] Honey Lemon Soda / 青春特調蜂蜜檸檬蘇打 - 07 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689833___01_NF_WebRip_AI2160p_HEVC_10bit_E_AC_3`() {
    kotlin.run {
    val r = parse("[不当舔狗制作组] 坂本日常 - 01 [NF WebRip AI2160p HEVC-10bit E-AC-3][日英双语音频 官方简繁内封字幕]")
    assertEquals("01..01", r.episodeRange.toString())
    assertEquals("CHS, CHT, ENG, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id
        })
    assertEquals("4K", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689832_____Chi_Chikyuu_no_Undou_ni_Tsuite_19_WebRip_HEVC_AAC`() {
    kotlin.run {
    val r =
        parse("[北宇治字幕组] 地。-关于地球的运动- / Chi. Chikyuu no Undou ni Tsuite [19][WebRip][HEVC_AAC][简繁日内封]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689831_____Chi_Chikyuu_no_Undou_ni_Tsuite_19_WebRip_HEVC_AAC`() {
    kotlin.run {
    val r =
        parse("[北宇治字幕组] 地。-关于地球的运动- / Chi. Chikyuu no Undou ni Tsuite [19][WebRip][HEVC_AAC][简日内嵌]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689830_____Chi_Chikyuu_no_Undou_ni_Tsuite_19_WebRip_HEVC_AAC`() {
    kotlin.run {
    val r =
        parse("[北宇治字幕组] 地。-關於地球的運動- / Chi. Chikyuu no Undou ni Tsuite [19][WebRip][HEVC_AAC][繁日內嵌]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689829_Ao_no_Hako_Blue_Box_18_WebRip_HEVC_AAC`() {
    kotlin.run {
    val r =
        parse("[北宇治字幕组] 青之箱 / 青春之箱 / 青春盒子 / 蓝箱 / Ao no Hako / Blue Box [18][WebRip][HEVC_AAC][简繁日内封]")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689828_Ao_no_Hako_Blue_Box_18_WebRip_HEVC_AAC`() {
    kotlin.run {
    val r = parse("[北宇治字幕组] 青之箱 / 蓝箱 / Ao no Hako / Blue Box [18][WebRip][HEVC_AAC][简日内嵌]")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689827_Ao_no_Hako_Blue_Box_17_WebRip_HEVC_AAC`() {
    kotlin.run {
    val r = parse("[北宇治字幕组] 青春之箱 / 青春盒子 / Ao no Hako / Blue Box [18][WebRip][HEVC_AAC][繁日內嵌]")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689826___01_NF_WebRip_1080p_HEVC_10bit_E_AC_3`() {
    kotlin.run {
    val r = parse("[不当舔狗制作组] 坂本日常 - 01 [NF WebRip 1080p HEVC-10bit E-AC-3][日英双语音频 官方简繁内封字幕]")
    assertEquals("01..01", r.episodeRange.toString())
    assertEquals("CHS, CHT, ENG, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id
        })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689825_jibaketa_Punch_21_WEB_1920x1080_AVC_AAC_YUE_MUSE_CHT`() {
    kotlin.run {
    val r = parse("[jibaketa自載][代理商粵語]熊熊勇闖異世界Punch! [粵語+影片內字幕](WEB 1920x1080 AVC AAC YUE MUSE CHT)")
    assertEquals("null", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689824_jibaketa_WEB_1920x1080_AVC_AAC_YUE_MUSE_CHT`() {
    kotlin.run {
    val r = parse("[jibaketa自載][代理商粵語]熊熊勇闖異世界 [粵語+影片內字幕](WEB 1920x1080 AVC AAC YUE MUSE CHT)")
    assertEquals("null", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689823_Honey_Lemon_Soda_06_1080p`() {
    kotlin.run {
    val r = parse("[云光字幕组] 蜂蜜柠檬苏打水 Honey Lemon Soda [06][简体双语][1080p]招募翻译")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689822_LoliHouse_Ameku_Takao_s_Detective_Karte___06_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[悠哈璃羽字幕社&LoliHouse] 天久鹰央的推理病历表 / Ameku Takao's Detective Karte - 06 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689821_Sentai_Red_Isekai_de_Boukensha_ni_Naru_06_1080p_2025_1`() {
    kotlin.run {
    val r =
        parse("[猎户手抄部] 红战士在异世界成了冒险者 / Sentai Red Isekai de Boukensha ni Naru [06] [1080p] [繁日内嵌] [2025年1月番]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689820_Sentai_Red_Isekai_de_Boukensha_ni_Naru_06_1080p_2025_1`() {
    kotlin.run {
    val r =
        parse("[猎户手抄部] 战队红战士在异世界当冒险者 / Sentai Red Isekai de Boukensha ni Naru [06] [1080p] [简日内嵌] [2025年1月番]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689819_ANi_Re___11_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] Re：從零開始的異世界生活 第三季 - 11 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("11..11", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689818_DBD_Raws_Kono_Subarashii_Sekai_ni_Shukufuku_o_21_Kurenai_Densetsu_1080P_BDRip_HEVC_10bit_FLACx2_MKV__21`() {
    kotlin.run {
    val r =
        parse("[DBD-Raws][剧场版 为美好的世界献上祝福！ 红传说/Kono Subarashii Sekai ni Shukufuku o! Kurenai Densetsu][1080P][BDRip][HEVC-10bit][简繁外挂][FLACx2][MKV](この素晴らしい世界に祝福を! 紅伝説)")
    assertEquals("S?", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689817_DBD_Raws_Kono_Subarashii_Sekai_ni_Shukufuku_o_21_2_01_10TV_OVA_1080P_BDRip_HEVC_10bit_FLACx2_MKV__21_2`() {
    kotlin.run {
    val r =
        parse("[DBD-Raws][为美好的世界献上祝福！ 第二季/Kono Subarashii Sekai ni Shukufuku o! 2][01-10TV全集+OVA][1080P][BDRip][HEVC-10bit][简繁外挂][FLACx2][MKV](この素晴らしい世界に祝福を! 2)")
    assertEquals("01..10+OVA", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689816_DBD_Raws_Kono_Subarashii_Sekai_ni_Shukufuku_o_21_01_10TV_OVA_1080P_BDRip_HEVC_10bit_FLACx2_MKV__21`() {
    kotlin.run {
    val r =
        parse("[DBD-Raws][为美好的世界献上祝福！ 第一季/Kono Subarashii Sekai ni Shukufuku o!][01-10TV全集+OVA+特典映像][1080P][BDRip][HEVC-10bit][简繁外挂][FLACx2][MKV](この素晴らしい世界に祝福を!)")
    assertEquals("01..10+OVA", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public fun `689815_ANi___08_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] 遲早是最強的鍊金術師？ - 08 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689814_146_147_WEB_MKV_2160P`() {
    kotlin.run {
    val r = parse("[神印王座][第146-147集][国语中字][WEB-MKV][2160P]")
    assertEquals("146..147", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689813_LoliHouse_Sararyman_ga_Isekai_ni_Ittara_Shitennou_ni_Natta_Hanashi___08_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 平凡上班族到异世界当上了四天王的故事 / Sararyman ga Isekai ni Ittara Shitennou ni Natta Hanashi - 08 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689811_Chibi_Maruko_chan_II_1467_2025_02_16_GB_JP_1080P_MP4`() {
    kotlin.run {
    val r =
        parse("[夜莺家族][樱桃小丸子第二期(Chibi Maruko-chan II)][1467]藤木教永泽滑冰&小丸子机智应对危机[2025.02.16][GB_JP][1080P][MP4]")
    assertEquals("1467..1467", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689810_1142_1143_1080P_MP4`() {
    kotlin.run {
    val r = parse("[風車字幕組十四周年][名偵探柯南][1142-1143][亂步邸殺人事件（前後篇）][1080P][繁體][MP4]")
    assertEquals("1142..1143", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689809_1142_1143_1080P_MP4`() {
    kotlin.run {
    val r = parse("[风车字幕组十四周年][名侦探柯南][1142-1143][乱步邸杀人事件（前后篇）][1080P][简体][MP4]")
    assertEquals("1142..1143", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689808_1141_1080P_MP4`() {
    kotlin.run {
    val r = parse("[風車字幕組十四周年][名偵探柯南][1141][看家的毛利一家][1080P][繁體][MP4]")
    assertEquals("1141..1141", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689807_1141_1080P_MP4`() {
    kotlin.run {
    val r = parse("[风车字幕组十四周年][名侦探柯南][1141][看家的毛利一家][1080P][简体][MP4]")
    assertEquals("1141..1141", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689804_Bocchi_the_Rock_S01_1080p_Taiwanese_Hokkien_Dub_H_n_j_T_i_l_Mandarin_Hardsub`() {
    kotlin.run {
    val r =
        parse("Bocchi the Rock S01 孤獨搖滾 第一季 [1080p][Taiwanese Hokkien Dub][臺灣閩南語配音][Hàn-jī/Tâi-lô/Mandarin Hardsub][漢字/華語/臺羅字幕]")
    assertEquals("S1", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689801_LoliHouse_Kono_Kaisha_ni_Suki_na_Hito_ga_Imasu___06_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 这公司有我喜欢的人 / この会社に好きな人がいます / Kono Kaisha ni Suki na Hito ga Imasu - 06 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689800_Shikanoko_Nokonoko_Koshitantan_12_WebRip_HEVC_AAC`() {
    kotlin.run {
    val r =
        parse("[北宇治字幕组] 鹿乃子乃子乃子虎视眈眈 / Shikanoko Nokonoko Koshitantan [12][WebRip][HEVC_AAC][简繁日内封]")
    assertEquals("12..12", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689799_Shikanoko_Nokonoko_Koshitantan_12_WebRip_HEVC_AAC`() {
    kotlin.run {
    val r =
        parse("[北宇治字幕组] 鹿乃子乃子乃子虎视眈眈 / Shikanoko Nokonoko Koshitantan [12][WebRip][HEVC_AAC][简日内嵌]")
    assertEquals("12..12", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689798_Shikanoko_Nokonoko_Koshitantan_12_WebRip_HEVC_AAC`() {
    kotlin.run {
    val r =
        parse("[北宇治字幕组] 鹿乃子乃子乃子虎視眈眈 / Shikanoko Nokonoko Koshitantan [12][WebRip][HEVC_AAC][繁日內嵌]")
    assertEquals("12..12", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689797_Shikanoko_Nokonoko_Koshitantan_11_WebRip_HEVC_AAC`() {
    kotlin.run {
    val r =
        parse("[北宇治字幕组] 鹿乃子乃子乃子虎视眈眈 / Shikanoko Nokonoko Koshitantan [11][WebRip][HEVC_AAC][简繁日内封]")
    assertEquals("11..11", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689796_Shikanoko_Nokonoko_Koshitantan_11_WebRip_HEVC_AAC`() {
    kotlin.run {
    val r =
        parse("[北宇治字幕组] 鹿乃子乃子乃子虎视眈眈 / Shikanoko Nokonoko Koshitantan [11][WebRip][HEVC_AAC][简日内嵌]")
    assertEquals("11..11", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689795_Shikanoko_Nokonoko_Koshitantan_11_WebRip_HEVC_AAC`() {
    kotlin.run {
    val r =
        parse("[北宇治字幕组] 鹿乃子乃子乃子虎視眈眈 / Shikanoko Nokonoko Koshitantan [11][WebRip][HEVC_AAC][繁日內嵌]")
    assertEquals("11..11", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689792_LoliHouse_Amagami_san_Chi_no_Enmusubi___19_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 结缘甘神神社 / Amagami-san Chi no Enmusubi - 19 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689790_LoliHouse_Kinomi_Master___08_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 最弱技能《果实大师》 ～关于能无限食用技能果实（吃了就会死）这件事～ / Kinomi Master - 08 [WebRip 1080p HEVC-10bit AAC][无字幕]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689789_VCB_Studio_Summer_Time_Rendering_10_bit_1080p_HEVC_BDRip_Reseed_Fin`() {
    kotlin.run {
    val r =
        parse("[喵萌奶茶屋&VCB-Studio] 夏日重现 / Summer Time Rendering / サマータイムレンダ 10-bit 1080p HEVC BDRip [Reseed Fin]")
    assertEquals("S?", r.episodeRange.toString())
    assertEquals("JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689775_ANi_Hana_wa_Saku_Shura_no_Gotoku___07_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[ANi] Hana wa Saku Shura no Gotoku / 群花綻放、彷如修羅 - 07 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689774_Zenshuu_07_webRip_AVC_8bit_1080P_AAC`() {
    kotlin.run {
    val r = parse("[黑白字幕组]全修。 / Zenshuu. [07] [webRip] [AVC-8bit 1080P AAC] [繁日内嵌]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689773_Zenshuu_07_webRip_AVC_8bit_1080P_AAC`() {
    kotlin.run {
    val r = parse("[黑白字幕组]全修。 / Zenshuu. [07] [webRip] [AVC-8bit 1080P AAC] [简日内嵌]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689772_1_Funkan_dake_Furete_mo_Ii_yo_Share_House_no_Himitsu_Rule_06_1080p`() {
    kotlin.run {
    val r =
        parse("[桜都字幕组] 「只能触摸一分钟哦...」合租房的秘密规则。 / 「1 Funkan dake Furete mo Ii yo...」 Share House no Himitsu Rule. [06][1080p][简繁内封]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689771_1_Funkan_dake_Furete_mo_Ii_yo_Share_House_no_Himitsu_Rule_06_1080p`() {
    kotlin.run {
    val r =
        parse("[桜都字幕組] 「只能觸摸一分鐘哦...」合租房的秘密規則。 / 「1 Funkan dake Furete mo Ii yo...」 Share House no Himitsu Rule. [06][1080p][繁體內嵌]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689770_1_Funkan_dake_Furete_mo_Ii_yo_Share_House_no_Himitsu_Rule_06_1080p`() {
    kotlin.run {
    val r =
        parse("[桜都字幕组] 「只能触摸一分钟哦...」合租房的秘密规则。 / 「1 Funkan dake Furete mo Ii yo...」 Share House no Himitsu Rule. [06][1080p][简体内嵌]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689769_YUI_Yami_Shibai_14_07_x264_1080p_CHS`() {
    kotlin.run {
    val r = parse("【悠哈璃羽字幕社&西农YUI汉化组】[暗芝居 第十四季_Yami Shibai 14][07][x264 1080p][CHS]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689768_YUI_Yami_Shibai_14_07_x264_1080p_CHT`() {
    kotlin.run {
    val r = parse("【悠哈璃羽字幕社&西農YUI漢化組】[暗芝居 第十四季_Yami Shibai 14][07][x264 1080p][CHT]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689767_YUI_Yami_Shibai_14_06_x264_1080p_CHT`() {
    kotlin.run {
    val r = parse("【悠哈璃羽字幕社&西農YUI漢化組】[暗芝居 第十四季_Yami Shibai 14][06][x264 1080p][CHT]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689766_YUI_Yami_Shibai_14_06_x264_1080p_CHS`() {
    kotlin.run {
    val r = parse("【悠哈璃羽字幕社&西农YUI汉化组】[暗芝居 第十四季_Yami Shibai 14][06][x264 1080p][CHS]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689765_Billion_Meta_Lab_Momentary_Lily_06_1080P_HEVC_10bit`() {
    kotlin.run {
    val r =
        parse("[Billion Meta Lab] 昙花一现的百合 Momentary Lily [06][1080P][HEVC 10bit][简繁日内封][检索：刹那之花]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689764_Billion_Meta_Lab_Momentary_Lily_06_1080P`() {
    kotlin.run {
    val r = parse("[Billion Meta Lab] 曇花一現的百合 Momentary Lily [06][1080P][繁日內嵌][檢索：剎那之花]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689763_Billion_Meta_Lab_Momentary_Lily_06_1080P`() {
    kotlin.run {
    val r = parse("[Billion Meta Lab] 昙花一现的百合 Momentary Lily [06][1080P][简日内嵌][检索：刹那之花]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689762_____Chi_Chikyuu_no_Undou_ni_Tsuite_21_1080p_2024_10`() {
    kotlin.run {
    val r =
        parse("[猎户手抄部] 地。-关于地球的运动- / Chi. Chikyuu no Undou ni Tsuite [21] [1080p] [繁日内嵌] [2024年10月番]")
    assertEquals("21..21", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689761_____Chi_Chikyuu_no_Undou_ni_Tsuite_21_1080p_2024_10`() {
    kotlin.run {
    val r =
        parse("[猎户手抄部] 地。-关于地球的运动- / Chi. Chikyuu no Undou ni Tsuite [21] [1080p] [简日内嵌] [2024年10月番]")
    assertEquals("21..21", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689760_ANi_Amagamisan_Chi_no_Enmusubi___19_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[ANi] Amagamisan Chi no Enmusubi / 結緣甘神神社 - 19 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689759_LoliHouse_Mahoutsukai_no_Yakusoku___07_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 魔法使的约定 / Mahoutsukai no Yakusoku - 07 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689758_Strange_Raw_POK_TOON_17_WEB_DL_1080P_AVC_8Bits_AAC_2_0_2`() {
    kotlin.run {
    val r = parse("[Strange-Raw] POKÉTOON [17] [WEB-DL] [1080P AVC-8Bits AAC 2.0×2] [简体内嵌字幕]")
    assertEquals("17..17", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689755_YYQ_New_Doraemon_A_850_2025_02_15_AVC_1080P_GB_JP`() {
    kotlin.run {
    val r = parse("[夜莺家族&YYQ字幕组]New Doraemon 哆啦A梦新番[850][2025.02.15][AVC][1080P][GB_JP]")
    assertEquals("850..850", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689753_1_06_HEVC_x265_10bit_1080p`() {
    kotlin.run {
    val r = parse("[云歌字幕组][1月新番][欢迎来到日本，精灵小姐][06][HEVC][x265 10bit][1080p][简日双语]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689749_ANi___19_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[ANi] 妖怪學校的菜鳥老師 - 19 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689748_1_BanG_Dream_21_Ave_Mujica_05_1080p_MP4`() {
    kotlin.run {
    val r = parse("[漫猫字幕组][1月新番][BanG Dream! Ave Mujica][05][1080p][MP4][简中]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689747_1_BanG_Dream_21_Ave_Mujica_05_1080p_MP4`() {
    kotlin.run {
    val r = parse("[漫貓字幕組][1月新番][BanG Dream! Ave Mujica][05][1080p][MP4][繁中]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689746_1_BanG_Dream_21_Ave_Mujica_06_1080p_MP4`() {
    kotlin.run {
    val r = parse("[漫猫字幕组][1月新番][BanG Dream! Ave Mujica][06][1080p][MP4][简中]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689745_1_BanG_Dream_21_Ave_Mujica_06_1080p_MP4`() {
    kotlin.run {
    val r = parse("[漫貓字幕組][1月新番][BanG Dream! Ave Mujica][06][1080p][MP4][繁中]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689744_1_Magic_Maker_Isekai_Mahou_no_Tsukurikata_06_BIG5_MP4_1920X1080`() {
    kotlin.run {
    val r =
        parse("【幻櫻字幕組】【1月新番】【魔法製造者 ~異世界魔法的製作方法~ Magic Maker ~Isekai Mahou no Tsukurikata~】【06】【BIG5_MP4】【1920X1080】")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689743_1_Magic_Maker_Isekai_Mahou_no_Tsukurikata_06_GB_MP4_1920X1080`() {
    kotlin.run {
    val r =
        parse("【幻樱字幕组】【1月新番】【魔法制造者 ~异世界魔法的制作方法~ Magic Maker ~Isekai Mahou no Tsukurikata~】【06】【GB_MP4】【1920X1080】")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689740_97_WEB_MKV_2160P`() {
    kotlin.run {
    val r = parse("[遮天][第97集][国语中字][WEB-MKV][2160P]")
    assertEquals("97..97", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689727_BanG_Dream_21_Ave_Mujica_2025_WEB_06_1080p`() {
    kotlin.run {
    val r = parse("[冷番补完字幕组][BanG Dream! Ave Mujica][2025][WEB][06][1080p][内封简繁中字]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689719_LoliHouse_Akuyaku_Reijou_Tensei_Ojisan___06_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 大叔转生恶役大小姐 / Akuyaku Reijou Tensei Ojisan - 06 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689718_LoliHouse_Akuyaku_Reijou_Tensei_Ojisan___05_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 大叔转生恶役大小姐 / Akuyaku Reijou Tensei Ojisan - 05 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689717_LoliHouse_Akuyaku_Reijou_Tensei_Ojisan___04_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 大叔转生恶役大小姐 / Akuyaku Reijou Tensei Ojisan - 04 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("04..04", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689716_LoliHouse_Akuyaku_Reijou_Tensei_Ojisan___03_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 大叔转生恶役大小姐 / Akuyaku Reijou Tensei Ojisan - 03 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("03..03", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689715_LoliHouse_Akuyaku_Reijou_Tensei_Ojisan___02_WebRip_1080p_HEVC_10bit_AAC_v2`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 大叔转生恶役大小姐 / Akuyaku Reijou Tensei Ojisan - 02 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]v2")
    assertEquals("02..02", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689714_LoliHouse_Akuyaku_Reijou_Tensei_Ojisan___01_WebRip_1080p_HEVC_10bit_AAC_v2`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 大叔转生恶役大小姐 / Akuyaku Reijou Tensei Ojisan - 01 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]v2")
    assertEquals("01..01", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689711_DBD_Raws_4K_HDR_The_Super_Dimension_Fortress_Macross___Do_You_Remember_Love_2160P_UHDBDRip_HEVC_10bit_FLAC_MKV_Choujikuu_Yousai_Macross_Ai_Oboete_Imasuka`() {
    kotlin.run {
    val r =
        parse("[DBD-Raws][4K_HDR][超时空要塞 可曾记得爱/The Super Dimension Fortress Macross - Do You Remember Love?][完全版+院线版][2160P][UHDBDRip][HEVC-10bit][简繁日双语外挂][FLAC][MKV](Choujikuu Yousai Macross: Ai Oboete Imasuka/超時空要塞マクロス 愛・おぼえていますか)")
    assertEquals("S?", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689709_jibaketa_HOY_Pocket_Monsters_2023___35_WEB_1920x1080_AVC_AAC_PGS_SRT_iCABLE_YUE_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][HOY粵語]寶可夢 地平線 / Pocket Monsters (2023) - 35 [粵語+內封繁體中文字幕](WEB 1920x1080 AVC AAC PGS+SRT iCABLE YUE CHT)")
    assertEquals("35..35", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689707_Hana_wa_Saku_Shura_no_Gotoku_01_TVRip_1080P_HEVC_MKV`() {
    kotlin.run {
    val r =
        parse("[诸神字幕组][群花绽放、彷如修罗][Hana wa Saku, Shura no Gotoku][01][TVRip][简繁日语字幕][1080P][HEVC MKV]")
    assertEquals("01..01", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689706_Hana_wa_Saku_Shura_no_Gotoku_01_TVRip_1080P_CHS_MP4`() {
    kotlin.run {
    val r =
        parse("[诸神字幕组][群花绽放、彷如修罗][Hana wa Saku, Shura no Gotoku][01][TVRip][简日双语字幕][1080P][CHS MP4]")
    assertEquals("01..01", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689703_VCB_Studio_Dainanaoji_10_bit_1080p_HEVC_BDRip_Fin`() {
    kotlin.run {
    val r =
        parse("[VCB-Studio] 转生为第七王子，随心所欲的魔法学习之路 / Tensei shitara Dainana Ouji Datta node, Kimama ni Majutsu wo Kiwamemasu / 転生したら第七王子だったので、気ままに魔術を極めます 10-bit 1080p HEVC BDRip [Fin]")
    assertEquals("S?", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689702_LoliHouse_SAKAMOTO_DAYS___06_WebRip_1080p_HEVC_10bit_AACx2`() {
    kotlin.run {
    val r =
        parse("[北宇治字幕组&LoliHouse] 坂本日常 / SAKAMOTO DAYS - 06 [WebRip 1080p HEVC-10bit AACx2][简繁日内封字幕]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689701_LoliHouse_Kuroiwa_Medaka_ni_Watashi_no_Kawaii_ga_Tsuujinai___07_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 黑岩目高不把我的可爱放在眼里 / Kuroiwa Medaka ni Watashi no Kawaii ga Tsuujinai - 07 [WebRip 1080p HEVC-10bit AAC][无字幕]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689700_LoliHouse_____Chi_Chikyuu_no_Undou_ni_Tsuite_18_WebRip_1080p_HEVC_10bit_AAC_ASSx2`() {
    kotlin.run {
    val r =
        parse("[北宇治字幕组&LoliHouse] 地。-关于地球的运动- / Chi. Chikyuu no Undou ni Tsuite 18 [WebRip 1080p HEVC-10bit AAC ASSx2][简繁日内封字幕]")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689699_LoliHouse_____Chi_Chikyuu_no_Undou_ni_Tsuite_17_WebRip_1080p_HEVC_10bit_AAC_ASSx2`() {
    kotlin.run {
    val r =
        parse("[北宇治字幕组&LoliHouse] 地。-关于地球的运动- / Chi. Chikyuu no Undou ni Tsuite 17 [WebRip 1080p HEVC-10bit AAC ASSx2][简繁日内封字幕]")
    assertEquals("17..17", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689698_ANi___16_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[搬運][ANi] 平凡職業造就世界最強 第三季 - 16 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("16..16", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689697_ANi_Promise_of_Wizard___07_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[搬運][ANi] Promise of Wizard / 魔法使的約定 - 07 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689696_ANi___08_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[搬運][ANi] 平凡上班族到異世界當上了四天王的故事 - 08 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689695_ANi_I_Have_a_Crush_at_Work___07_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[搬運][ANi] I Have a Crush at Work / 這公司有我喜歡的人 - 07 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689694_ANi_Neko_ni_Tensei_Shita_Ojisan___19_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[搬運][ANi] Neko ni Tensei Shita Ojisan / 轉生成貓咪的大叔 - 19 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689693_10_DAIMA_Dragon_Ball_DAIMA_17_BIG5_MP4_1920X1080`() {
    kotlin.run {
    val r = parse("【幻櫻字幕組】【10月新番】【龍珠DAIMA Dragon Ball DAIMA】【17】【BIG5_MP4】【1920X1080】")
    assertEquals("17..17", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689692_10_DAIMA_Dragon_Ball_DAIMA_17_GB_MP4_1920X1080`() {
    kotlin.run {
    val r = parse("【幻樱字幕组】【10月新番】【龙珠DAIMA Dragon Ball DAIMA】【17】【GB_MP4】【1920X1080】")
    assertEquals("17..17", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689691_10_DAIMA_Dragon_Ball_DAIMA_17_BIG5_MP4_1280X720`() {
    kotlin.run {
    val r = parse("【幻櫻字幕組】【10月新番】【龍珠DAIMA Dragon Ball DAIMA】【17】【BIG5_MP4】【1280X720】")
    assertEquals("17..17", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("720P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689690_10_DAIMA_Dragon_Ball_DAIMA_17_GB_MP4_1280X720`() {
    kotlin.run {
    val r = parse("【幻樱字幕组】【10月新番】【龙珠DAIMA Dragon Ball DAIMA】【17】【GB_MP4】【1280X720】")
    assertEquals("17..17", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("720P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689686_Sub_Nihon_e_Youkoso_Elf_san_03_1080P_AVC_AAC_WebRip`() {
    kotlin.run {
    val r =
        parse("[霜庭云花Sub][欢迎来到日本 妖精小姐 / 日本へようこそエルフさん。 / Nihon e Youkoso Elf-san.][03][1080P][AVC AAC][简日双语][WebRip]")
    assertEquals("03..03", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689685_Sub_Nihon_e_Youkoso_Elf_san_04_1080P_AVC_AAC_WebRip`() {
    kotlin.run {
    val r =
        parse("[霜庭云花Sub][欢迎来到日本 妖精小姐 / 日本へようこそエルフさん。 / Nihon e Youkoso Elf-san.][04][1080P][AVC AAC][简日双语][WebRip]")
    assertEquals("04..04", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689684_Sub_BanG_Dream_21_Ave_Mujica_06_1080P_AVC_AAC_WebRip`() {
    kotlin.run {
    val r = parse("[霜庭雲花Sub][BanG Dream! Ave Mujica][06][1080P][AVC AAC][繁日雙語][WebRip]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689683_Sub_BanG_Dream_21_Ave_Mujica_06_1080P_AVC_AAC_WebRip`() {
    kotlin.run {
    val r = parse("[霜庭云花Sub][BanG Dream! Ave Mujica][06][1080P][AVC AAC][简日双语][WebRip]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689682_Hana_wa_Saku_Shura_no_Gotoku_06_1080p`() {
    kotlin.run {
    val r = parse("[桜都字幕組] 群花綻放、彷如修羅 / Hana wa Saku, Shura no Gotoku [06][1080p][繁體內嵌]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689681_Hana_wa_Saku_Shura_no_Gotoku_06_1080p`() {
    kotlin.run {
    val r = parse("[桜都字幕组] 群花绽放、彷如修罗 / Hana wa Saku, Shura no Gotoku [06][1080p][简体内嵌]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689680_Hana_wa_Saku_Shura_no_Gotoku_06_1080p`() {
    kotlin.run {
    val r = parse("[桜都字幕组] 群花绽放、彷如修罗 / Hana wa Saku, Shura no Gotoku [06][1080p][简繁内封]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689678_BanG_Dream_21_Ave_Mujica_06_1080p`() {
    kotlin.run {
    val r = parse("[萌樱字幕组] BanG Dream! Ave Mujica [06][1080p]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689677_BanG_Dream_21_Ave_Mujica_05_WebRip_HEVC_AAC`() {
    kotlin.run {
    val r = parse("[北宇治字幕组] BanG Dream! Ave Mujica / 颂乐人偶 [05][WebRip][HEVC_AAC][简繁日内封]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689676_BanG_Dream_21_Ave_Mujica_05_WebRip_HEVC_AAC`() {
    kotlin.run {
    val r = parse("[北宇治字幕组] BanG Dream! Ave Mujica / 頌樂人偶 [05][WebRip][HEVC_AAC][繁日內嵌]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689675_BanG_Dream_21_Ave_Mujica_05_WebRip_HEVC_AAC`() {
    kotlin.run {
    val r = parse("[北宇治字幕组] BanG Dream! Ave Mujica / 颂乐人偶 [05][WebRip][HEVC_AAC][简日内嵌]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689674_Kusuriya_no_Hitorigoto_30_WebRip_HEVC_AAC`() {
    kotlin.run {
    val r =
        parse("[北宇治字幕组] 药屋少女的呢喃 / 药屋少女的独语 / Kusuriya no Hitorigoto [30][WebRip][HEVC_AAC][简繁日内封]")
    assertEquals("30..30", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689673_Kusuriya_no_Hitorigoto_30_WebRip_HEVC_AAC`() {
    kotlin.run {
    val r =
        parse("[北宇治字幕组] 药屋少女的呢喃 / 药屋少女的独语 / Kusuriya no Hitorigoto [30][WebRip][HEVC_AAC][简日内嵌]")
    assertEquals("30..30", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689672_Kusuriya_no_Hitorigoto_30_WebRip_HEVC_AAC`() {
    kotlin.run {
    val r =
        parse("[北宇治字幕组] 藥屋少女的呢喃 / 藥屋少女的獨語 / Kusuriya no Hitorigoto [30][WebRip][HEVC_AAC][繁日內嵌]")
    assertEquals("30..30", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689671_1_Funkan_dake_Furete_mo_Ii_yo_Share_House_no_Himitsu_Rule_05_1080p`() {
    kotlin.run {
    val r =
        parse("[桜都字幕组] 「只能触摸一分钟哦...」合租房的秘密规则。 / 「1 Funkan dake Furete mo Ii yo...」 Share House no Himitsu Rule. [05][1080p][简繁内封]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689670_1_Funkan_dake_Furete_mo_Ii_yo_Share_House_no_Himitsu_Rule_05_1080p`() {
    kotlin.run {
    val r =
        parse("[桜都字幕組] 「只能觸摸一分鐘哦...」合租房的秘密規則。 / 「1 Funkan dake Furete mo Ii yo...」 Share House no Himitsu Rule. [05][1080p][繁體內嵌]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689669_1_Funkan_dake_Furete_mo_Ii_yo_Share_House_no_Himitsu_Rule_05_1080p`() {
    kotlin.run {
    val r =
        parse("[桜都字幕组] 「只能触摸一分钟哦...」合租房的秘密规则。 / 「1 Funkan dake Furete mo Ii yo...」 Share House no Himitsu Rule. [05][1080p][简体内嵌]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689668_01_12_720P_MP4`() {
    kotlin.run {
    val r = parse("[轻之国度字幕组][恋语轻唱/恰如细语般的恋歌][01-12][720P][MP4]")
    assertEquals("01..12", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("720P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689666_1_Kusuriya_no_Hitorigoto_30_1080P_MP4_GB_JP`() {
    kotlin.run {
    val r =
        parse("[漫猫字幕社&猫恋汉化组][1月新番][药屋少女的呢喃][Kusuriya no Hitorigoto][30][1080P][MP4][GB&JP][简日双语]")
    assertEquals("30..30", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689665_1_Kusuriya_no_Hitorigoto_30_1080P_MP4_BIG5_JP`() {
    kotlin.run {
    val r =
        parse("[漫貓字幕社&貓戀漢化組][1月新番][藥師少女的獨語][Kusuriya no Hitorigoto][30][1080P][MP4][BIG5&JP][繁日雙語]")
    assertEquals("30..30", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689664_158_159_WEB_MKV_2160P`() {
    kotlin.run {
    val r = parse("[吞噬星空][第158-159集][国语中字][WEB-MKV][2160P]")
    assertEquals("158..159", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689663_Babanba_Banban_Vampire_03_1080P_CHT`() {
    kotlin.run {
    val r = parse("【悠哈璃羽字幕社】[嘻嘻嘻嘻吸血鬼][Babanba Banban Vampire][03][1080P][CHT]")
    assertEquals("03..03", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689662_Babanba_Banban_Vampire_03_1080P_CHS`() {
    kotlin.run {
    val r = parse("【悠哈璃羽字幕社】[嘻嘻嘻嘻吸血鬼][Babanba Banban Vampire][03][1080P][CHS]")
    assertEquals("03..03", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689661_Class_no_Daikirai_na_Joshi_to_Kekkon_Suru_Koto_ni_Natta_07_1080p`() {
    kotlin.run {
    val r =
        parse("[桜都字幕组] 我和班上最讨厌的女生结婚了。 / Class no Daikirai na Joshi to Kekkon Suru Koto ni Natta. [07][1080p][简繁内封]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689660_Class_no_Daikirai_na_Joshi_to_Kekkon_Suru_Koto_ni_Natta_07_1080p`() {
    kotlin.run {
    val r =
        parse("[桜都字幕組] 我和班上最討厭的女生結婚了。 / Class no Daikirai na Joshi to Kekkon Suru Koto ni Natta. [07][1080p][繁體內嵌]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689659_Class_no_Daikirai_na_Joshi_to_Kekkon_Suru_Koto_ni_Natta_07_1080p`() {
    kotlin.run {
    val r =
        parse("[桜都字幕组] 我和班上最讨厌的女生结婚了。 / Class no Daikirai na Joshi to Kekkon Suru Koto ni Natta. [07][1080p][简体内嵌]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689655_Ameku_Takao_no_Suiri_Karte_06_1080p_CHT`() {
    kotlin.run {
    val r = parse("【悠哈璃羽字幕社】[天久鷹央的推理病歷表_Ameku Takao no Suiri Karte][06][1080p][CHT]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689654_Ameku_Takao_no_Suiri_Karte_06_1080p_CHS`() {
    kotlin.run {
    val r = parse("【悠哈璃羽字幕社】[天久鹰央的推理病历表_Ameku Takao no Suiri Karte][06][1080p][CHS]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689653_NEO_QSW_Aquarion_Myth_of_Emotions_05_WEBRIP_AVC_1080P`() {
    kotlin.run {
    val r =
        parse("[NEO·QSW]想星的阿克艾利昂 情感神话 想星のアクエリオン Aquarion: Myth of Emotions 05[WEBRIP AVC 1080P]（搜索用：想星的大天使）")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689652_Grisaia_Phantom_Trigger_07_1080p`() {
    kotlin.run {
    val r = parse("[桜都字幕组] 灰色：幻影扳机 / Grisaia：Phantom Trigger [07][1080p][简繁内封]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689651_Grisaia_Phantom_Trigger_07_1080p`() {
    kotlin.run {
    val r = parse("[桜都字幕組] 灰色：幻影扳機 / Grisaia：Phantom Trigger [07][1080p][繁體內嵌]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689650_Grisaia_Phantom_Trigger_07_1080p`() {
    kotlin.run {
    val r = parse("[桜都字幕组] 灰色：幻影扳机 / Grisaia：Phantom Trigger [07][1080p][简体内嵌]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689649_Grisaia_Phantom_Trigger_06_1080p`() {
    kotlin.run {
    val r = parse("[桜都字幕组] 灰色：幻影扳机 / Grisaia：Phantom Trigger [06][1080p][简繁内封]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689648_Grisaia_Phantom_Trigger_06_1080p`() {
    kotlin.run {
    val r = parse("[桜都字幕組] 灰色：幻影扳機 / Grisaia：Phantom Trigger [06][1080p][繁體內嵌]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689647_NEO_QSW_Aquarion_Myth_of_Emotions_04_WEBRIP_AVC_1080P`() {
    kotlin.run {
    val r =
        parse("[NEO·QSW]想星的阿克艾利昂 情感神话 想星のアクエリオン Aquarion: Myth of Emotions 04[WEBRIP AVC 1080P]（搜索用：想星的大天使）")
    assertEquals("04..04", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689646_Grisaia_Phantom_Trigger_06_1080p`() {
    kotlin.run {
    val r = parse("[桜都字幕组] 灰色：幻影扳机 / Grisaia：Phantom Trigger [06][1080p][简体内嵌]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689645_Grisaia_Phantom_Trigger_05_1080p`() {
    kotlin.run {
    val r = parse("[桜都字幕组] 灰色：幻影扳机 / Grisaia：Phantom Trigger [05][1080p][简繁内封]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689644_Grisaia_Phantom_Trigger_05_1080p`() {
    kotlin.run {
    val r = parse("[桜都字幕組] 灰色：幻影扳機 / Grisaia：Phantom Trigger [05][1080p][繁體內嵌]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689643_Grisaia_Phantom_Trigger_05_1080p`() {
    kotlin.run {
    val r = parse("[桜都字幕组] 灰色：幻影扳机 / Grisaia：Phantom Trigger [05][1080p][简体内嵌]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689642_VCB_Studio_Isekai_Shikkaku_10_bit_1080p_HEVC_BDRip_Fin`() {
    kotlin.run {
    val r = parse("[VCB-Studio] 异世界失格 / Isekai Shikkaku / 異世界失格 10-bit 1080p HEVC BDRip [Fin]")
    assertEquals("S?", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689641_DBD_Raws_Sei_Juushi_Bismarck_01_51TV_USA_Ver_1080P_BDRip_HEVC_10bit_FLAC_MKV_Star_Musketeer_Bismark`() {
    kotlin.run {
    val r =
        parse("[DBD-Raws][星铳士俾斯麦/Sei Juushi Bismarck][01-51TV全集][美版/USA.Ver][1080P][BDRip][HEVC-10bit][FLAC][MKV](星銃士 ビスマルク/Star Musketeer Bismark)")
    assertEquals("01..51", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689640_29_1080p`() {
    kotlin.run {
    val r = parse("[萌樱字幕组] 药屋少女的呢喃 [29][1080p][简繁日内封]")
    assertEquals("29..29", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689638_ANi_Season_2___17_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[搬運][ANi] 金肉人 完美超人始祖篇 Season 2 - 17 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("17..17", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689637_ANi_100___18_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[搬運][ANi] 超超超超超喜歡你的 100 個女朋友 - 18 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689636_ANi___07_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[搬運][ANi] 我想蹺掉太子妃培訓 - 07 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689635_ANi_Mashin_Creator_Wataru___06_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[搬運][ANi] Mashin Creator Wataru / 魔神創造傳 - 06 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689634_ANi_ToiletBound_Hanakokun_S02___06_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[搬運][ANi] ToiletBound Hanakokun S02 / 地縛少年花子君 第二季 - 06 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689633_ANi___19_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[搬運][ANi] 香格里拉・開拓異境～糞作獵手挑戰神作～ 第二季 - 19 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689632_S___08_AMZN_WebRip_AI2160p_HEVC_10bit_E_AC_3`() {
    kotlin.run {
    val r =
        parse("[新沸羊羊制作] S级怪兽《贝希摩斯》被误认成小猫，成为精灵女孩的骑士(宠物)一起生活 - 08 [AMZN WebRip AI2160p HEVC-10bit E-AC-3][官方简繁内封字幕]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689631_Skymoon_Raws_100_Hyakkano___18_ViuTV_WEB_RIP_1080p_AVC_AAC_CHT_SRT_MKV`() {
    kotlin.run {
    val r =
        parse("[Skymoon-Raws] 超超超超超喜歡你的100個女朋友 / Hyakkano - 18 [ViuTV][WEB-RIP][1080p][AVC AAC][CHT][SRT][MKV]")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689630_Skymoon_Raws_Kisaki_Kyouiku_kara_Nigetai_Watashi___07_ViuTV_WEB_DL_1080p_AVC_AAC`() {
    kotlin.run {
    val r =
        parse("[Skymoon-Raws] 我想蹺掉太子妃培訓 / Kisaki Kyouiku kara Nigetai Watashi - 07 [ViuTV][WEB-DL][1080p][AVC AAC]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689629_Skymoon_Raws_Mashin_Souzouden_Wataru___06_ViuTV_WEB_DL_1080p_AVC_AAC`() {
    kotlin.run {
    val r =
        parse("[Skymoon-Raws] 魔神創造傳 / Mashin Souzouden Wataru - 06 [ViuTV][WEB-DL][1080p][AVC AAC]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689628_Skymoon_Raws_2_Shangri_La_Frontier_2nd_Season___19_ViuTV_WEB_DL_1080p_AVC_AAC`() {
    kotlin.run {
    val r =
        parse("[Skymoon-Raws] 香格里拉·開拓異境～糞作獵手挑戰神作～第2季 / Shangri-La Frontier 2nd Season - 19 [ViuTV][WEB-DL][1080p][AVC AAC]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689627_10_Touhai_Ura_Rate_Mahjong_Touhai_Roku_18_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★10月新番★[凍牌 / Touhai: Ura Rate Mahjong Touhai Roku][18][1080p][繁體][招募翻譯]")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689626_10_Touhai_Ura_Rate_Mahjong_Touhai_Roku_18_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★10月新番★[冻牌 / Touhai: Ura Rate Mahjong Touhai Roku][18][1080p][简体][招募翻译]")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689625_FSD_MIRAI_DAYS_05_1080P`() {
    kotlin.run {
    val r = parse("【FSD粉羽社】魔法使光之美少女！！～MIRAI DAYS～[05][1080P][简体中字]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689624___07_CR_WebRip_AI2160p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r = parse("[新沸羊羊制作] 我独自升级 第二季 －起于暗影－ - 07 [CR WebRip AI2160p HEVC-10bit AAC][官方简繁内封字幕]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689622_jibaketa_ViuTV_Captain_Tsubasa_Season_2___Junior_Youth_Hen___20_WEB_1920x1080_AVC_AACx2_SRT_ViuTV_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][ViuTV粵語]隊長小翼第二季 青少年篇 / 世少篇 / Captain Tsubasa Season 2 - Junior Youth Hen - 20 (WEB 1920x1080 AVC AACx2 SRT ViuTV CHT)")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689619_LoliHouse_Medalist___07_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r = parse("[喵萌奶茶屋&LoliHouse] 金牌得主 / Medalist - 07 [WebRip 1080p HEVC-10bit AAC][简繁日内封字幕]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689618_LoliHouse_Ao_no_Exorcist_Yosuga_hen___07_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[豌豆字幕组&LoliHouse] 青之驱魔师 终夜篇 / Ao no Exorcist Yosuga-hen - 07 [WebRip 1080p HEVC-10bit AAC][简繁外挂字幕]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public fun `689617_01_Medalist_07_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[金牌得主 / Medalist][07][1080p][繁日雙語][招募翻譯]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689616_01_Medalist_07_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[金牌得主 / Medalist][07][1080p][简日双语][招募翻译]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689615_Ao_no_Exorcist_Yosuga_Hen_07v2_1080P_MP4`() {
    kotlin.run {
    val r = parse("【豌豆字幕組】[青之驅魔師 終夜篇 / Ao no Exorcist _Yosuga Hen][07v2][繁體][1080P][MP4]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689614_Ao_no_Exorcist_Yosuga_Hen_07v2_1080P_MP4`() {
    kotlin.run {
    val r = parse("【豌豆字幕组】[青之驱魔师 终夜篇 / Ao no Exorcist _Yosuga Hen][07v2][简体][1080P][MP4]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689613_SAKAMOTO_DAYS_06_WebRip_HEVC_AAC`() {
    kotlin.run {
    val r = parse("[北宇治字幕组] 坂本日常 / SAKAMOTO DAYS [06][WebRip][HEVC_AAC][简繁日內封]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689612_SAKAMOTO_DAYS_06_WebRip_HEVC_AAC`() {
    kotlin.run {
    val r = parse("[北宇治字幕组] 坂本日常 / SAKAMOTO DAYS [06][WebRip][HEVC_AAC][简日內嵌]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689611_SAKAMOTO_DAYS_06_WebRip_HEVC_AAC`() {
    kotlin.run {
    val r = parse("[北宇治字幕组] 坂本日常 / SAKAMOTO DAYS [06][WebRip][HEVC_AAC][繁日內嵌]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689610_____Rurouni_Kenshin_Meiji_Kenkaku_Romantan___Kyoto_Douran_42_1080p_2024_10`() {
    kotlin.run {
    val r =
        parse("[猎户手抄部] 神剑闯江湖 -明治剑客浪漫谭- 京都动乱 / Rurouni Kenshin：Meiji Kenkaku Romantan - Kyoto Douran [42] [1080p] [繁日内嵌] [2024年10月番]")
    assertEquals("42..42", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689609_____Rurouni_Kenshin_Meiji_Kenkaku_Romantan___Kyoto_Douran_42_1080p_2024_10`() {
    kotlin.run {
    val r =
        parse("[猎户手抄部] 浪客剑心 -明治剑客浪漫谭- 京都动乱 / Rurouni Kenshin: Meiji Kenkaku Romantan - Kyoto Douran [42] [1080p] [简日内嵌] [2024年10月番]")
    assertEquals("42..42", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689608_Strange_Raw_Sentai_Red_Isekai_de_Boukensha_ni_Naru_S01_06_Bilibili_WEB_DL_1080P_AVC_8Bits_AAC_2_0`() {
    kotlin.run {
    val r =
        parse("[Strange-Raw] 战队红战士在异世界当冒险者 第一季 / Sentai Red Isekai de Boukensha ni Naru S01 [06] [Bilibili] [WEB-DL] [1080P AVC-8Bits AAC 2.0] [简日内嵌字幕]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689607_Ao_no_Hako_Blue_Box_17_WebRip_HEVC_AAC`() {
    kotlin.run {
    val r =
        parse("[北宇治字幕组] 青之箱 / 青春之箱 / 青春盒子 / 蓝箱 / Ao no Hako / Blue Box [17][WebRip][HEVC_AAC][简繁日内封]")
    assertEquals("17..17", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689606_Ao_no_Hako_Blue_Box_17_WebRip_HEVC_AAC`() {
    kotlin.run {
    val r = parse("[北宇治字幕组] 青春之箱 / 青春盒子 / Ao no Hako / Blue Box [17][WebRip][HEVC_AAC][繁日內嵌]")
    assertEquals("17..17", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689605_Ao_no_Hako_Blue_Box_17_WebRip_HEVC_AAC`() {
    kotlin.run {
    val r = parse("[北宇治字幕组] 青之箱 / 蓝箱 / Ao no Hako / Blue Box [17][WebRip][HEVC_AAC][简日内嵌]")
    assertEquals("17..17", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689604_SW_77_83_1080P_AVC_MP4_CHS_JP`() {
    kotlin.run {
    val r = parse("【SW字幕组】[宠物小精灵 / 宝可梦 地平线 烈空坐之升龙崛起][77-83][简日双语字幕][1080P][AVC][MP4][CHS_JP]")
    assertEquals("77..83", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689603_A_Doraemon1979_2000_3_11_1080P_WEB_MKV`() {
    kotlin.run {
    val r =
        parse("[哆啦字幕组][哆啦A梦大山版附映中篇 Doraemon1979][2000.3.11][1080P][WEB][简日&繁日][奶奶的回忆][数码修复版][MKV]")
    assertEquals("null", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689602_LoliHouse_S_Beheneko___08_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] S级怪兽《贝希摩斯》被误认成小猫，成为精灵女孩的骑士（宠物）一起生活 / Beheneko - 08 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689599_MingY_Re_Bocchi_the_Rock_21_Re_BDRip_1080p_HEVC_OPUS`() {
    kotlin.run {
    val r =
        parse("[MingY] 劇場總集篇 孤獨搖滾！ Re: / Bocchi the Rock! Re [劇場版][BDRip][1080p][HEVC OPUS][繁日內嵌]")
    assertEquals("S?", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689598_MingY_Re_Bocchi_the_Rock_21_Re_BDRip_1080p_HEVC_OPUS`() {
    kotlin.run {
    val r =
        parse("[MingY] 剧场总集篇 孤独摇滚！ Re: / Bocchi the Rock! Re [剧场版][BDRip][1080p][HEVC OPUS][简日内嵌]")
    assertEquals("S?", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689597_Himitsu_no_Aipri_45`() {
    kotlin.run {
    val r = parse("[雪飄工作室][ひみつのアイプリ/Himitsu_no_Aipri/秘密的美妙公主][45][简繁外挂](检索:/美妙旋律/美妙天堂/美妙頻道/美妙魔法)")
    assertEquals("45..45", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public fun `689596_Himitsu_no_Aipri_720P_45`() {
    kotlin.run {
    val r =
        parse("[雪飄工作室][ひみつのアイプリ/Himitsu_no_Aipri/秘密的美妙公主][720P][45][繁](检索:/美妙旋律/美妙天堂/美妙頻道/美妙魔法)")
    assertEquals("45..45", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("720P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689595_LoliHouse_Shangri_La_Frontier___44_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] Shangri-La Frontier / 香格里拉·开拓异境～粪作猎手挑战神作～ - 44 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("44..44", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689593_GM_Team_Renegade_Immortal_2023_76_AVC_GB_1080P`() {
    kotlin.run {
    val r = parse("[GM-Team][国漫][仙逆][Renegade Immortal][2023][76][AVC][GB][1080P]")
    assertEquals("76..76", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689592_LoliHouse_Fuguushoku_Kanteishi_ga_Jitsu_wa_Saikyou_Datta___05_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 不幸职业【鉴定士】实则最强 / Fuguushoku 'Kanteishi' ga Jitsu wa Saikyou Datta - 05 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689591_LoliHouse_Fuguushoku_Kanteishi_ga_Jitsu_wa_Saikyou_Datta___04_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 不幸职业【鉴定士】实则最强 / Fuguushoku 'Kanteishi' ga Jitsu wa Saikyou Datta - 04 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("04..04", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689590_LoliHouse_Fuguushoku_Kanteishi_ga_Jitsu_wa_Saikyou_Datta___03_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 不幸职业【鉴定士】实则最强 / Fuguushoku 'Kanteishi' ga Jitsu wa Saikyou Datta - 03 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("03..03", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689589_LoliHouse_Botsuraku_Yotei_no_Kizoku_dakedo_Hima_Datta_kara_Mahou_wo_Kiwametemita___05_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 虽然我是注定没落的贵族，闲来无事只好来深究魔法 / Botsuraku Yotei no Kizoku dakedo, Hima Datta kara Mahou wo Kiwametemita - 05 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689588_jibaketa_TVB_Let_s_Camp_21_Yuru_Camp_Season_3___04_BD_1920x1080_x264_AACx2_SRT_TVB_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&壓制][TVB粵語]Let's Camp!露營少女/搖曳露營/Yuru Camp Season 3 - 04 [粵日雙語+內封繁體中文字幕][BD 1920x1080 x264 AACx2 SRT TVB CHT]")
    assertEquals("04..04", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689586_76_WEB_MKV_2160P`() {
    kotlin.run {
    val r = parse("[仙逆][第76集][国语中字][WEB-MKV][2160P]")
    assertEquals("76..76", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689585_GM_Team_The_Eternal_Realm_2024_17_HEVC_GB_4K`() {
    kotlin.run {
    val r = parse("[GM-Team][国漫][长生界][The Eternal Realm][2024][17][HEVC][GB][4K]")
    assertEquals("17..17", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689584_GM_Team_The_Eternal_Realm_2024_16_HEVC_GB_4K`() {
    kotlin.run {
    val r = parse("[GM-Team][国漫][长生界][The Eternal Realm][2024][16][HEVC][GB][4K]")
    assertEquals("16..16", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689583_GM_Team_The_Eternal_Realm_2024_15_HEVC_GB_4K`() {
    kotlin.run {
    val r = parse("[GM-Team][国漫][长生界][The Eternal Realm][2024][15][HEVC][GB][4K]")
    assertEquals("15..15", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689582_VCB_Studio_Kuusen_Madoushi_Kouhosei_no_Kyoukan_10_bit_1080p_HEVC_BDRip_Fin`() {
    kotlin.run {
    val r =
        parse("[VCB-Studio] 空战魔导士候补生的教官 / Kuusen Madoushi Kouhosei no Kyoukan / 空戦魔導士候補生の教官 10-bit 1080p HEVC BDRip [Fin]")
    assertEquals("S?", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689581_DBD_Raws_Kono_Subarashii_Sekai_ni_Shukufuku_o_21_3_01_11TV_1080P_BDRip_HEVC_10bit_FLACx2_MKV__21_3`() {
    kotlin.run {
    val r =
        parse("[DBD-Raws][为美好的世界献上祝福！ 第三季/Kono Subarashii Sekai ni Shukufuku o! 3][01-11TV全集+特典映像][1080P][BDRip][HEVC-10bit][简繁外挂][FLACx2][MKV](この素晴らしい世界に祝福を! 3)")
    assertEquals("01..11", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public fun `689579_GM_Team_Swallowed_Star_2021_158_AVC_GB_1080P`() {
    kotlin.run {
    val r = parse("[GM-Team][国漫][吞噬星空][Swallowed Star][2021][158][AVC][GB][1080P]")
    assertEquals("158..158", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689578_GM_Team_Swallowed_Star_2021_157_AVC_GB_1080P`() {
    kotlin.run {
    val r = parse("[GM-Team][国漫][吞噬星空][Swallowed Star][2021][157][AVC][GB][1080P]")
    assertEquals("157..157", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689577_GM_Team_Swallowed_Star_2021_156_AVC_GB_1080P`() {
    kotlin.run {
    val r = parse("[GM-Team][国漫][吞噬星空][Swallowed Star][2021][156][AVC][GB][1080P]")
    assertEquals("156..156", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689576_GM_Team_Renegade_Immortal_2023_75_AVC_GB_1080P`() {
    kotlin.run {
    val r = parse("[GM-Team][国漫][仙逆][Renegade Immortal][2023][75][AVC][GB][1080P]")
    assertEquals("75..75", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689575_GM_Team_Renegade_Immortal_2023_74_AVC_GB_1080P`() {
    kotlin.run {
    val r = parse("[GM-Team][国漫][仙逆][Renegade Immortal][2023][74][AVC][GB][1080P]")
    assertEquals("74..74", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689574_GM_Team_Renegade_Immortal_2023_73_AVC_GB_1080P`() {
    kotlin.run {
    val r = parse("[GM-Team][国漫][仙逆][Renegade Immortal][2023][73][AVC][GB][1080P]")
    assertEquals("73..73", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689573_LoliHouse_A_Aparida___06_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 离开A级队伍的我，和从前的弟子往迷宫深处迈进 / Aparida - 06 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689572_LoliHouse_NEET_Kunoichi___07_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 我与尼特女忍者的莫名同居生活 / NEET Kunoichi - 07 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689571_DBD_Raws_The_Super_Dimension_Fortress_Macross___Do_You_Remember_Love_1080P_BDRip_HEVC_10bit_FLAC_MKV_Choujikuu_Yousai_Macross_Ai_Oboete_Imasuka`() {
    kotlin.run {
    val r =
        parse("[DBD-Raws][超时空要塞 可曾记得爱/The Super Dimension Fortress Macross - Do You Remember Love?][完全版+院线版][1080P][BDRip][HEVC-10bit][简繁日双语外挂][FLAC][MKV](Choujikuu Yousai Macross: Ai Oboete Imasuka/超時空要塞マクロス 愛・おぼえていますか)")
    assertEquals("S?", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public fun `689568_You_and_Idol_Precure_1080p_03_Q`() {
    kotlin.run {
    val r =
        parse("[雪飘工作室][キミとアイドルプリキュア♪/You and Idol Precure♪/与你同为 偶像光之美少女♪][1080p][03][简繁外挂](检索:Q娃)")
    assertEquals("03..03", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public fun `689567_You_and_Idol_Precure_720p_03_Q`() {
    kotlin.run {
    val r =
        parse("[雪飘工作室][キミとアイドルプリキュア♪/You and Idol Precure♪/与你同为 偶像光之美少女♪][720p][03][简体内嵌](检索:Q娃)")
    assertEquals("03..03", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("720P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689566_You_and_Idol_Precure_720p_03_Q`() {
    kotlin.run {
    val r =
        parse("[雪飄工作室][キミとアイドルプリキュア♪/You and Idol Precure♪/与你同为 偶像光之美少女♪][720p][03][繁體內嵌](檢索:Q娃)")
    assertEquals("03..03", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("720P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689563_LoliHouse_____Ore_dake_Level_Up_na_Ken_Solo_Leveling___19_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 我独自升级 -起于暗影- / Ore dake Level Up na Ken / Solo Leveling - 19 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689561_LoliHouse_Tasokare_Hotel___07_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[喵萌奶茶屋&LoliHouse] 黄昏旅店 / Tasokare Hotel - 07 [WebRip 1080p HEVC-10bit AAC][简繁日内封字幕]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689560_LoliHouse_Tasokare_Hotel___06_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[喵萌奶茶屋&LoliHouse] 黄昏旅店 / Tasokare Hotel - 06 [WebRip 1080p HEVC-10bit AAC][简繁日内封字幕]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689559_LoliHouse_Tasokare_Hotel___05_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[喵萌奶茶屋&LoliHouse] 黄昏旅店 / Tasokare Hotel - 05 [WebRip 1080p HEVC-10bit AAC][简繁日内封字幕]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689557_ANi___07_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[搬運][ANi] 在沖繩喜歡上的女孩方言講得太過令人困擾 - 07 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689556_ANi_Blue_Exorcist___07_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[搬運][ANi] Blue Exorcist / 青之驅魔師 終夜篇 - 07 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689555_ANi_A___06_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[搬運][ANi] 離開 A 級隊伍的我，和從前的弟子往迷宮深處邁進 - 06 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689554_ANi___07_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[搬運][ANi] 我與尼特女忍者的莫名同居生活 - 07 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689553_ANi_Solo_Leveling_S02___19_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[搬運][ANi] Solo Leveling S02 / 我獨自升級 第二季 －起於闇影－ - 19 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689552_ANi_UniteUp_21__Uni_Birth____06_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[搬運][ANi] UniteUp! 眾星齊聚 -Uni：Birth- - 06 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689551_ANi_S___08_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[搬運][ANi] S 級怪獸《貝希摩斯》被誤認成小貓，成為精靈女孩的騎士（寵物）一起生活 - 08 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689550_ANi_Blue_Miburo___18_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[搬運][ANi] Blue Miburo / 青之壬生浪 - 18 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689549__21_21_MIRAI_DAYS_Maho_Girls_Precure_21_21__21_21_1080p_06_Q`() {
    kotlin.run {
    val r =
        parse("[雪飘工作室][魔法つかいプリキュア!!~MIRAI DAYS~/Maho_Girls_Precure!!/魔法少女 光之美少女!!~未来的日子~][1080p][06][简繁外挂](检索:Q娃)")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public fun `689548__21_21_MIRAI_DAYS_Maho_Girls_Precure_21_21__21_21_720p_06_Q`() {
    kotlin.run {
    val r =
        parse("[雪飘工作室][魔法つかいプリキュア!!~MIRAI DAYS~/Maho_Girls_Precure!!/魔法少女 光之美少女!!~未来的日子~][720p][06][简体内嵌](检索:Q娃)")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("720P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689547__21_21_MIRAI_DAYS_Maho_Girls_Precure_21_21__21_21_720p_06_Q`() {
    kotlin.run {
    val r =
        parse("[雪飄工作室][魔法つかいプリキュア!!~MIRAI DAYS~/Maho_Girls_Precure!!/魔法少女 光之美少女!!~未來的日子~][720p][06][繁體內嵌](檢索:Q娃)")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("720P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689546_10_18_1080P_MP4`() {
    kotlin.run {
    val r = parse("【动漫国字幕组&澄空学园&华盟字幕社&千夏字幕组】★10月新番[冻牌][18][1080P][简体][MP4]")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689545_10_18_1080P_MP4`() {
    kotlin.run {
    val r = parse("【動漫國字幕組&澄空學園&華盟字幕社&千夏字幕組】★10月新番[凍牌][18][1080P][繁體][MP4]")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689542_10_Chi_____Chi_Chikyuu_no_Undou_ni_Tsuite_19_1080p`() {
    kotlin.run {
    val r =
        parse("【喵萌奶茶屋】★10月新番★[Chi。-關於地球的運動- / Chi. Chikyuu no Undou ni Tsuite][19][1080p][繁日雙語][招募翻譯]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689541_10_Chi_____Chi_Chikyuu_no_Undou_ni_Tsuite_18_1080p`() {
    kotlin.run {
    val r =
        parse("【喵萌奶茶屋】★10月新番★[Chi。-關於地球的運動- / Chi. Chikyuu no Undou ni Tsuite][18][1080p][繁日雙語][招募翻譯]")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689540_10_Chi_____Chi_Chikyuu_no_Undou_ni_Tsuite_17_1080p`() {
    kotlin.run {
    val r =
        parse("【喵萌奶茶屋】★10月新番★[Chi。-關於地球的運動- / Chi. Chikyuu no Undou ni Tsuite][17][1080p][繁日雙語][招募翻譯]")
    assertEquals("17..17", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689539_10_Chi_____Chi_Chikyuu_no_Undou_ni_Tsuite_16_1080p`() {
    kotlin.run {
    val r =
        parse("【喵萌奶茶屋】★10月新番★[Chi。-關於地球的運動- / Chi. Chikyuu no Undou ni Tsuite][16][1080p][繁日雙語][招募翻譯]")
    assertEquals("16..16", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689538_1153_WEBRIP_MKV_1080P`() {
    kotlin.run {
    val r = parse("[银色子弹字幕组][名侦探柯南][第1153集 屋久岛的山姬（前篇）][WEBRIP][简繁日多语MKV][1080P]")
    assertEquals("1153..1153", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689537_1153_WEBRIP_MP4_1080P`() {
    kotlin.run {
    val r = parse("[银色子弹字幕组][名侦探柯南][第1153集 屋久島的山姬（前篇）][WEBRIP][繁日雙語MP4][1080P]")
    assertEquals("1153..1153", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689536_1153_WEBRIP_MP4_1080P`() {
    kotlin.run {
    val r = parse("[银色子弹字幕组][名侦探柯南][第1153集 屋久岛的山姬（前篇）][WEBRIP][简日双语MP4][1080P]")
    assertEquals("1153..1153", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689535_01_Tasokare_Hotel_07_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[黃昏旅店 / Tasokare Hotel][07][1080p][繁日雙語][招募翻譯]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689534_01_Tasokare_Hotel_06_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[黃昏旅店 / Tasokare Hotel][06][1080p][繁日雙語][招募翻譯]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689533_01_Tasokare_Hotel_05_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[黃昏旅店 / Tasokare Hotel][05][1080p][繁日雙語][招募翻譯]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689532_Skymoon_Raws_____Ore_dake_Level_Up_na_Ken_Season_2__07_ViuTV_WEB_DL_1080p_AVC_AAC`() {
    kotlin.run {
    val r =
        parse("[Skymoon-Raws] 我獨自升級 第二季 -起於闇影- / Ore dake Level Up na Ken Season 2- 07 [ViuTV][WEB-DL][1080p][AVC AAC]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689531_Babanba_Banban_Vampire___06_NetFlix_1920x1080_AVC_AAC_MKV`() {
    kotlin.run {
    val r = parse("[天月搬運組] 吸吸吸吸吸血鬼 / Babanba Banban Vampire - 06 (NetFlix 1920x1080 AVC AAC MKV)")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689528_SAKAMOTO_DAYS_S01E07_Jurassic_Bastard_1080p_NF_WEB_DL_DDP5_1_H_264_MULTi_VARYG_Multi_Audio_Multi_Subs`() {
    kotlin.run {
    val r =
        parse("SAKAMOTO DAYS S01E07 Jurassic Bastard 1080p NF WEB-DL DDP5.1 H 264 MULTi-VARYG (Multi-Audio, Multi-Subs)")
    assertEquals("264..264", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689527_Kuroiwa_Medaka_ni_Watashi_no_Kawaii_ga_Tsuujinai_06_webRip_AVC_8bit_1080P_AAC`() {
    kotlin.run {
    val r =
        parse("[黑白字幕组]我的可爱对黑岩目高不管用 / Kuroiwa Medaka ni Watashi no Kawaii ga Tsuujinai [06] [webRip] [AVC-8bit 1080P AAC] [繁日内嵌]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689526_Kuroiwa_Medaka_ni_Watashi_no_Kawaii_ga_Tsuujinai_06_webRip_AVC_8bit_1080P_AAC`() {
    kotlin.run {
    val r =
        parse("[黑白字幕组]我的可爱对黑岩目高不管用 / Kuroiwa Medaka ni Watashi no Kawaii ga Tsuujinai [06] [webRip] [AVC-8bit 1080P AAC] [简日内嵌]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689525_Skymoon_Raws_S_Neko_to_Machigawarete_Elf_Musume_no_Pet_toshite_Kurashitemasu___08_ViuTV_WEB_DL_1080p_AVC_AAC`() {
    kotlin.run {
    val r =
        parse("[Skymoon-Raws] S級怪獸《貝希摩斯》被誤認成小貓，成為精靈女孩的騎士（寵物）一起生活 / Neko to Machigawarete Elf Musume no Pet toshite Kurashitemasu - 08 [ViuTV][WEB-DL][1080p][AVC AAC]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689523_jibaketa_TVB_Shinkalion_Change_the_World___29_WEB_1920x1080_AVC_AAC_SRT_TVB_YUE_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][TVB粵語]新幹線戰士 改變世界 / 新幹線變形機器人 變革世代 / Shinkalion Change the World - 29 [粵語+內封繁體中文字幕][WEB 1920x1080 AVC AAC SRT TVB YUE CHT]")
    assertEquals("29..29", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689520___18_CR_WebRip_AI2160p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r = parse("[新沸羊羊制作] 龙珠大魔 - 18 [CR WebRip AI2160p HEVC-10bit AAC][官方简繁内封字幕]")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689519_DBFC_018_1080P_MP4`() {
    kotlin.run {
    val r = parse("【DBFC字幕组】【龙珠 大魔】【018】【中日双语字幕】【1080P】【MP4】")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689518_Strange_Raw_SAKAMOTO_DAYS_S01_06_Bilibili_WEB_DL_1080P_AVC_8Bits_AAC_2_0`() {
    kotlin.run {
    val r =
        parse("[Strange-Raw] 坂本日常 第一季 / SAKAMOTO DAYS S01 [06] [Bilibili] [WEB-DL] [1080P AVC-8Bits AAC 2.0] [简日内嵌字幕]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689517_LoliHouse_Kurakon___07_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 我和班上最讨厌的女生结婚了。 / クラスの大嫌いな女子と結婚することになった。 / Kurakon - 07 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689514_LoliHouse_Nihon_e_Youkoso_Elf_san___06_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 精灵来日 / 歡迎來到日本，妖精小姐。 / 日本へようこそエルフさん。 / Nihon e Youkoso Elf-san. - 06 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689513___06_CR_WebRip_AI2160p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r = parse("[新沸羊羊制作] 药师少女的独语 第二季 - 06 [CR WebRip AI2160p HEVC-10bit AAC][官方简繁内封字幕]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689512_1140_3_1080P_MP4`() {
    kotlin.run {
    val r = parse("[風車字幕組][名偵探柯南][1140][女子推理社3][1080P][繁體][MP4]")
    assertEquals("1140..1140", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689511_1140_3_1080P_MP4`() {
    kotlin.run {
    val r = parse("[风车字幕组][名侦探柯南][1140][女子推理社3][1080P][简体][MP4]")
    assertEquals("1140..1140", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689510_06_GB_CN_HEVC_opus_1080p`() {
    kotlin.run {
    val r = parse("【极影字幕·毁片党】 这家公司里有我喜欢的人 第06集 GB_CN HEVC_opus 1080p")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689509_134_WEB_MKV_2160P`() {
    kotlin.run {
    val r = parse("[斗破苍穹年番][第134集][国语中字][WEB-MKV][2160P]")
    assertEquals("134..134", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689508_jibaketa_ViuTV_Wonderful_Precure_21___41_WEB_1920x1080_AVC_AAC_YUE`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][ViuTV粵語]美妙寵物 光之美少女！ / Wonderful Precure! - 41 [粵語+無字幕] (WEB 1920x1080 AVC AAC YUE)")
    assertEquals("41..41", r.episodeRange.toString())
    assertEquals("CHC", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689507_H_Enc_Gimai_Seikatsu_BDRip_1080p_HEVC_FLAC`() {
    kotlin.run {
    val r = parse("[H-Enc] 义妹生活 / Gimai Seikatsu (BDRip 1080p HEVC FLAC)")
    assertEquals("S?", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689506_LoliHouse_Izure_Saikyou_no_Renkinjutsushi___07_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 迟早是最强的炼金术师？ / Izure Saikyou no Renkinjutsushi? - 07 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689505_LoliHouse_Ishura_S2___06_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[喵萌奶茶屋&LoliHouse] 异修罗 第二季 / Ishura S2 - 06 [WebRip 1080p HEVC-10bit AAC][简繁日内封字幕]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689504_Re_Re_Zero_kara_Hajimeru_Isekai_Seikatsu_60_WebRip_1080p_AVC_AAC`() {
    kotlin.run {
    val r =
        parse("【百冬練習組】【Re: 從零開始的異世界的生活_Re꞉ Zero kara Hajimeru Isekai Seikatsu】[60][WebRip 1080p AVC AAC][繁體]")
    assertEquals("60..60", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689503_Re_Re_Zero_kara_Hajimeru_Isekai_Seikatsu_60_WebRip_1080p_AVC_AAC`() {
    kotlin.run {
    val r =
        parse("【百冬练习组】【Re: 从零开始的异世界的生活_Re꞉ Zero kara Hajimeru Isekai Seikatsu】[60][WebRip 1080p AVC AAC][简体]")
    assertEquals("60..60", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689501_VCB_Studio_Seiken_Tsukai_no_World_Break_10_bit_1080p_HEVC_BDRip_Fin`() {
    kotlin.run {
    val r =
        parse("[VCB-Studio] 圣剑使的禁咒咏唱 / Seiken Tsukai no World Break / 聖剣使いの禁呪詠唱 10-bit 1080p HEVC BDRip [Fin]")
    assertEquals("S?", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689500_DBD_Raws_Shoushimin_Series_01_10TV_1080P_BDRip_HEVC_10bit_FLAC_MKV`() {
    kotlin.run {
    val r =
        parse("[DBD-Raws][小市民系列/Shoushimin Series][01-10TV全集+特典映像][1080P][BDRip][HEVC-10bit][简繁日双语外挂][FLAC][MKV](小市民シリーズ)")
    assertEquals("01..10", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689497_LoliHouse_Act_2_Act_2_Unnamed_Memory_Act_2___18_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[喵萌奶茶屋&LoliHouse] 无名记忆 Act.2 / 無名記憶 Act.2 / Unnamed Memory Act.2 - 18 [WebRip 1080p HEVC-10bit AAC][简繁日内封字幕]")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689496_LoliHouse_Dragon_Ball_Daima___18_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 龙珠 大魔 / Dragon Ball Daima - 18 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689495_01_Honey_Lemon_Soda_04_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[蜂蜜檸檬蘇打 / Honey Lemon Soda][04][1080p][繁體]")
    assertEquals("04..04", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689494_01_Honey_Lemon_Soda_04_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[蜂蜜柠檬苏打 / Honey Lemon Soda][04][1080p][简体]")
    assertEquals("04..04", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689493_01_Honey_Lemon_Soda_03_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[蜂蜜檸檬蘇打 / Honey Lemon Soda][03][1080p][繁體]")
    assertEquals("03..03", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689492_01_Honey_Lemon_Soda_03_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[蜂蜜柠檬苏打 / Honey Lemon Soda][03][1080p][简体]")
    assertEquals("03..03", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689491_130_WEB_MKV_2160P`() {
    kotlin.run {
    val r = parse("[凡人修仙传][第130集][国语中字][WEB-MKV][2160P]")
    assertEquals("130..130", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689490_10_Chi_____Chi_Chikyuu_no_Undou_ni_Tsuite_19_1080p`() {
    kotlin.run {
    val r =
        parse("【喵萌奶茶屋】★10月新番★[Chi。-关于地球的运动- / Chi. Chikyuu no Undou ni Tsuite][19][1080p][简日双语][招募翻译]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689489_10_Chi_____Chi_Chikyuu_no_Undou_ni_Tsuite_18_1080p`() {
    kotlin.run {
    val r =
        parse("【喵萌奶茶屋】★10月新番★[Chi。-关于地球的运动- / Chi. Chikyuu no Undou ni Tsuite][18][1080p][简日双语][招募翻译]")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689488_01_Tasokare_Hotel_07_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[黄昏旅店 / Tasokare Hotel][07][1080p][简日双语][招募翻译]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689487_LoliHouse_Douse_Koishite_Shimaunda___06_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 终究、与你相恋 / Douse, Koishite Shimaunda - 06 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689486_Kusuriya_no_Hitorigoto_30_1080P_MP4`() {
    kotlin.run {
    val r = parse("【豌豆字幕组】[药屋少女的呢喃（药师少女的独语）/ Kusuriya no Hitorigoto][30][简体][1080P][MP4]")
    assertEquals("30..30", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689485_Kusuriya_no_Hitorigoto_30_1080P_MP4`() {
    kotlin.run {
    val r = parse("【豌豆字幕組】[藥屋少女的呢喃（藥師少女的獨語）/ Kusuriya no Hitorigoto][30][繁體][1080P][MP4]")
    assertEquals("30..30", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689484_ANi___18_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[搬運][ANi] 凍牌~地下麻將鬥牌錄~ - 18 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689483_ANi_Sorairo_Utility___07_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[搬運][ANi] Sorairo Utility / 一桿青空 - 07 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689482_ANi___06_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[搬運][ANi] 雖然是公會的櫃檯小姐，但因為不想加班所以打算獨自討伐迷宮頭目 - 06 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689481_ANi___07_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[搬運][ANi] 我和班上最討厭的女生結婚了。 - 07 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689480_ANi_Farmagia_FARMAGIA___06_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[搬運][ANi] Farmagia / FARMAGIA 魔農傳記 - 06 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689479_ANi_Tasokare_Hotel___07_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[搬運][ANi] Tasokare Hotel / 黃昏旅店 - 07 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689478_ANi_Welcome_to_Japan_Ms_Elf___06_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[搬運][ANi] Welcome to Japan Ms Elf / 歡迎來到日本，妖精小姐。 - 06 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689477_ANi__21_21_Divinez_DELUXE___05_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[搬運][ANi] 卡片戰鬥!! 先導者 Divinez 第三季「DELUXE篇」 - 05 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689476_57_58_WEBRIP_MP4_MP4_MKV_1080P`() {
    kotlin.run {
    val r = parse("[银色子弹字幕组][名侦探柯南][第57-58集 福尔摩斯迷杀人事件][WEBRIP][简日双语MP4/繁日雙語MP4/简繁日多语MKV][1080P]")
    assertEquals("57..58", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689470_Skymoon_Raws_Dragonball_DaiMa___18_ViuTV_WEB_DL_1080p_AVC_AAC`() {
    kotlin.run {
    val r = parse("[Skymoon-Raws] 龍珠大魔 / Dragonball DaiMa - 18 [ViuTV][WEB-DL][1080p][AVC AAC]")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689468_LoliHouse_Myth_of_Emotions_Sousei_no_Aquarion_Myth_of_Emotions___06_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 想星的大天使 Myth of Emotions / Sousei no Aquarion: Myth of Emotions - 06 [WebRip 1080p HEVC-10bit AAC][无字幕]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689467_Skymoon_Raws_Guild_no_Uketsukejou_desu_ga_Zangyou_wa_Iya_nanode_Boss_wo_Solo_Toubatsu_Shiyou_to_Omoimasu___06_ViuTV_WEB_DL_1080p_AVC_AAC`() {
    kotlin.run {
    val r =
        parse("[Skymoon-Raws] 雖然是公會的櫃檯小姐，但因為不想加班所以打算獨自討伐迷宮頭目 / Guild no Uketsukejou desu ga, Zangyou wa Iya nanode Boss wo Solo Toubatsu Shiyou to Omoimasu - 06 [ViuTV][WEB-DL][1080p][AVC AAC]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689466_01_Act_2_Unnamed_Memory_Act_2_18_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[無名記憶 Act.2 / Unnamed Memory Act.2][18][1080p][繁日雙語][招募翻譯時軸]")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689465_01_Act_2_Unnamed_Memory_Act_2_18_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[无名记忆 Act.2 / Unnamed Memory Act.2][18][1080p][简日双语][招募翻译时轴]")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689464_Dungeon_ni_Deai_o_Motomeru_no_wa_Machigatte_Iru_Darouka_Familia_Myth_V_13_1080p`() {
    kotlin.run {
    val r =
        parse("[桜都字幕组] 在地下城寻求邂逅是否搞错了什么 第五季 / Dungeon ni Deai o Motomeru no wa Machigatte Iru Darouka： Familia Myth V [13][1080p][繁体内嵌]")
    assertEquals("13..13", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689463_Dungeon_ni_Deai_o_Motomeru_no_wa_Machigatte_Iru_Darouka_Familia_Myth_V_13_1080p`() {
    kotlin.run {
    val r =
        parse("[桜都字幕组] 在地下城寻求邂逅是否搞错了什么 第五季 / Dungeon ni Deai o Motomeru no wa Machigatte Iru Darouka： Familia Myth V [13][1080p][简体内嵌]")
    assertEquals("13..13", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689462_Dungeon_ni_Deai_o_Motomeru_no_wa_Machigatte_Iru_Darouka_Familia_Myth_V_13_1080p`() {
    kotlin.run {
    val r =
        parse("[桜都字幕组] 在地下城寻求邂逅是否搞错了什么 第五季 / Dungeon ni Deai o Motomeru no wa Machigatte Iru Darouka： Familia Myth V [13][1080p][简繁内封]")
    assertEquals("13..13", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689461_Dungeon_ni_Deai_o_Motomeru_no_wa_Machigatte_Iru_Darouka_Familia_Myth_V_12_1080p`() {
    kotlin.run {
    val r =
        parse("[桜都字幕组] 在地下城寻求邂逅是否搞错了什么 第五季 / Dungeon ni Deai o Motomeru no wa Machigatte Iru Darouka： Familia Myth V [12][1080p][繁体内嵌]")
    assertEquals("12..12", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689460_Dungeon_ni_Deai_o_Motomeru_no_wa_Machigatte_Iru_Darouka_Familia_Myth_V_12_1080p`() {
    kotlin.run {
    val r =
        parse("[桜都字幕组] 在地下城寻求邂逅是否搞错了什么 第五季 / Dungeon ni Deai o Motomeru no wa Machigatte Iru Darouka： Familia Myth V [12][1080p][简体内嵌]")
    assertEquals("12..12", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689459_Dungeon_ni_Deai_o_Motomeru_no_wa_Machigatte_Iru_Darouka_Familia_Myth_V_12_1080p`() {
    kotlin.run {
    val r =
        parse("[桜都字幕组] 在地下城寻求邂逅是否搞错了什么 第五季 / Dungeon ni Deai o Motomeru no wa Machigatte Iru Darouka： Familia Myth V [12][1080p][简繁内封]")
    assertEquals("12..12", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689458_Kusuriya_no_Hitorigoto___30v2_NetFlix_1920x1080_AVC_AAC_MKV`() {
    kotlin.run {
    val r =
        parse("[天月搬運組] 藥師少女的獨語 (藥屋少女的呢喃) / Kusuriya no Hitorigoto - 30v2 (NetFlix 1920x1080 AVC AAC MKV)")
    assertEquals("30..30", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689457_jibaketa_Dandadan___08_WEB_1920x1080_AVC_AACx2_SRT_MUSE_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成][代理商粵語]膽大黨 / Dandadan - 08 [粵日雙語+內封繁體中文字幕](WEB 1920x1080 AVC AACx2 SRT MUSE CHT)")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689456_LoliHouse_Hana_wa_Saku_Shura_no_Gotoku___06_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[喵萌奶茶屋&LoliHouse] 群花绽放，仿如修罗 / Hana wa Saku Shura no Gotoku - 06 [WebRip 1080p HEVC-10bit AAC][简繁日内封字幕]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689455_Honey_Lemon_Soda_05_1080p`() {
    kotlin.run {
    val r = parse("[云光字幕组] 蜂蜜柠檬苏打水 Honey Lemon Soda [05][简体双语][1080p]招募翻译")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689454_____Ore_dake_Level_Up_na_Ken_S2_18v2_1080p_JaVer_2025_1`() {
    kotlin.run {
    val r =
        parse("[猎户压制部] 我独自升级 第二季 -起于暗影- / Ore dake Level Up na Ken S2 [18v2] [1080p] [繁日内嵌] [JaVer] [2025年1月番]")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689453_01_Hana_wa_Saku_Shura_no_Gotoku_06_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[群花綻放，彷如修羅 / Hana wa Saku Shura no Gotoku][06][1080p][繁日雙語][招募翻譯]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689452_01_Hana_wa_Saku_Shura_no_Gotoku_06_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[群花绽放，彷如修罗 / Hana wa Saku Shura no Gotoku][06][1080p][简日双语][招募翻译]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689451_____Ore_dake_Level_Up_na_Ken_S2_18v2_1080p_JaVer_2025_1`() {
    kotlin.run {
    val r =
        parse("[猎户压制部] 我独自升级 第二季 -起于暗影- / Ore dake Level Up na Ken S2 [18v2] [1080p] [简日内嵌] [JaVer] [2025年1月番]")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689450_____Ore_dake_Level_Up_na_Ken_S2_18_1080p_KoVer_2025_1`() {
    kotlin.run {
    val r =
        parse("[猎户压制部] 我独自升级 第二季 -起于暗影- / Ore dake Level Up na Ken S2 [18] [1080p] [简日内嵌] [KoVer] [2025年1月番]")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689449_Kusuriya_no_Hitorigoto___30_NetFlix_1920x1080_AVC_AAC_MKV`() {
    kotlin.run {
    val r =
        parse("[天月搬運組] 藥師少女的獨語 (藥屋少女的呢喃) / Kusuriya no Hitorigoto - 30 (NetFlix 1920x1080 AVC AAC MKV)")
    assertEquals("30..30", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689448_Amor_Kusuriya_no_Hitorigoto_30_WEBDL_1080P_MKV`() {
    kotlin.run {
    val r = parse("[Amor压制组][药师少女的独语 藥師少女的獨語 Kusuriya no Hitorigoto][30][简繁内封][WEBDL][1080P][MKV]")
    assertEquals("30..30", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689447_01_Ishura_S2_06_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[異修羅 第二季 / Ishura S2][06][1080p][繁日雙語][招募翻譯]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689445_LoliHouse_____Rurouni_Kenshin_2023___42_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 浪客剑心 -明治剑客浪漫谭- 京都动乱 / Rurouni Kenshin (2023) - 42 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("42..42", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689444_New_Doraemon_A_849_2025_02_08_AVC_1080P_GB_JP_MP4`() {
    kotlin.run {
    val r = parse("[梦蓝字幕组]New Doraemon 哆啦A梦新番[849][2025.02.08][AVC][1080P][GB_JP][MP4]")
    assertEquals("849..849", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689443_Hana_wa_Saku_Shura_no_Gotoku_05_1080p`() {
    kotlin.run {
    val r = parse("[桜都字幕组] 群花绽放、彷如修罗 / Hana wa Saku, Shura no Gotoku [05][1080p][简繁内封]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689442_Hana_wa_Saku_Shura_no_Gotoku_05_1080p`() {
    kotlin.run {
    val r = parse("[桜都字幕组] 群花绽放、彷如修罗 / Hana wa Saku, Shura no Gotoku [05][1080p][简体内嵌]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689441_Hana_wa_Saku_Shura_no_Gotoku_05_1080p`() {
    kotlin.run {
    val r = parse("[桜都字幕組] 群花綻放、彷如修羅 / Hana wa Saku, Shura no Gotoku [05][1080p][繁體內嵌]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689440_Okinawa_de_Suki_ni_Natta_Ko_ga_Hougen_Sugite_Tsura_Sugiru_06_1080p`() {
    kotlin.run {
    val r =
        parse("[桜都字幕组] 喜欢的冲绳妹说方言 / Okinawa de Suki ni Natta Ko ga Hougen Sugite Tsura Sugiru [06][1080p][简繁日内封]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689439_Okinawa_de_Suki_ni_Natta_Ko_ga_Hougen_Sugite_Tsura_Sugiru_06_1080p`() {
    kotlin.run {
    val r =
        parse("[桜都字幕组] 喜欢的冲绳妹说方言 / Okinawa de Suki ni Natta Ko ga Hougen Sugite Tsura Sugiru [06][1080p][简日内嵌]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689438_Okinawa_de_Suki_ni_Natta_Ko_ga_Hougen_Sugite_Tsura_Sugiru_06_1080p`() {
    kotlin.run {
    val r =
        parse("[桜都字幕組] 在沖繩喜歡上的女孩方言講得太過令人困擾 / Okinawa de Suki ni Natta Ko ga Hougen Sugite Tsura Sugiru [06][1080p][繁日內嵌]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689437_Magic_Maker_Isekai_Mahou_no_Tsukurikata_06_webRip_AVC_8bit_1080P_AAC`() {
    kotlin.run {
    val r =
        parse("[黑白字幕组]魔术发明家 ~异世界魔法的制作方法~ / Magic Maker: Isekai Mahou no Tsukurikata [06] [webRip] [AVC-8bit 1080P AAC] [繁日内嵌]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689436_Magic_Maker_Isekai_Mahou_no_Tsukurikata_06_webRip_AVC_8bit_1080P_AAC`() {
    kotlin.run {
    val r =
        parse("[黑白字幕组]魔术发明家 ~异世界魔法的制作方法~ / Magic Maker: Isekai Mahou no Tsukurikata [06] [webRip] [AVC-8bit 1080P AAC] [简日内嵌]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689435_DBD_Raws_Isekai_Nonbiri_Nouka_01_12TV_1080P_BDRip_HEVC_10bit_FLAC_MKV`() {
    kotlin.run {
    val r =
        parse("[DBD-Raws][异世界悠闲农家/Isekai Nonbiri Nouka][01-12TV全集+特典映像][1080P][BDRip][HEVC-10bit][简繁外挂][FLAC][MKV](異世界のんびり農家)")
    assertEquals("01..12", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public fun `689434_Unnamed_Memory_Act_2_18_1080p_2025_1`() {
    kotlin.run {
    val r = parse("[猎户手抄部] 无名记忆 第二季 / Unnamed Memory Act.2 [18] [1080p] [繁日内嵌] [2025年1月番]")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689433_Unnamed_Memory_Act_2_18_1080p_2025_1`() {
    kotlin.run {
    val r = parse("[猎户手抄部] 无名记忆 第二季 / Unnamed Memory Act.2 [18] [1080p] [简日内嵌] [2025年1月番]")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689432_Zenshuu_06_1080p`() {
    kotlin.run {
    val r = parse("[桜都字幕组] 全修。 / Zenshuu. [06][1080p][简体内嵌]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689431_Zenshuu_06_1080p`() {
    kotlin.run {
    val r = parse("[桜都字幕組] 全修。 / Zenshuu. [06][1080p][繁體內嵌]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689430_Zenshuu_06_1080p`() {
    kotlin.run {
    val r = parse("[桜都字幕组] 全修。 / Zenshuu. [06][1080p][简繁内封]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689429_Class_no_Daikirai_na_Joshi_to_Kekkon_Suru_Koto_ni_Natta_06_1080p`() {
    kotlin.run {
    val r =
        parse("[桜都字幕组] 我和班上最讨厌的女生结婚了。 / Class no Daikirai na Joshi to Kekkon Suru Koto ni Natta. [06][1080p][简体内嵌]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689428_Class_no_Daikirai_na_Joshi_to_Kekkon_Suru_Koto_ni_Natta_06_1080p`() {
    kotlin.run {
    val r =
        parse("[桜都字幕組] 我和班上最討厭的女生結婚了。 / Class no Daikirai na Joshi to Kekkon Suru Koto ni Natta. [06][1080p][繁體內嵌]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689427_Class_no_Daikirai_na_Joshi_to_Kekkon_Suru_Koto_ni_Natta_06_1080p`() {
    kotlin.run {
    val r =
        parse("[桜都字幕组] 我和班上最讨厌的女生结婚了。 / Class no Daikirai na Joshi to Kekkon Suru Koto ni Natta. [06][1080p][简繁内封]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689426_2_88_WEB_MKV_2160P`() {
    kotlin.run {
    val r = parse("[斗罗大陆2绝世唐门][第88集][国语中字][WEB-MKV][2160P]")
    assertEquals("88..88", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689424_jibaketa_TVB_Dog_Signal___06_WEB_1920x1080_AVC_AAC_SRT_TVB_YUE_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][TVB粵語]愛犬訊號 / Dog Signal - 06 [粵語+內封繁體中文字幕][WEB 1920x1080 AVC AAC SRT TVB YUE CHT]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689423_DBD_Raws_Senpai_wa_Otokonoko_01_12TV_1080P_BDRip_HEVC_10bit_FLAC_MKV`() {
    kotlin.run {
    val r =
        parse("[DBD-Raws][前辈是男孩子/Senpai wa Otokonoko][01-12TV全集][1080P][BDRip][HEVC-10bit][简繁日双语外挂][FLAC][MKV](先輩はおとこのこ)")
    assertEquals("01..12", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689421_DBD_Raws_Mahouka_Koukou_no_Rettousei_S3_01_13TV_1080P_BDRip_HEVC_10bit_FLAC_MKV_3`() {
    kotlin.run {
    val r =
        parse("[DBD-Raws][魔法科高中的劣等生 第三季/Mahouka Koukou no Rettousei S3][01-13TV全集][1080P][BDRip][HEVC-10bit][简繁外挂][FLAC][MKV](魔法科高校の劣等生 第3シーズン)")
    assertEquals("01..13", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public fun `689418_Arafou_Otoko_no_Isekai_Tsuuhan_Seikatsu_05_webRip_AVC_8bit_1080P_AAC`() {
    kotlin.run {
    val r =
        parse("[黑白字幕组]中年男的异世界网购生活 / Arafou Otoko no Isekai Tsuuhan Seikatsu [05] [webRip] [AVC-8bit 1080P AAC] [繁日内嵌]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689417_Arafou_Otoko_no_Isekai_Tsuuhan_Seikatsu_05_webRip_AVC_8bit_1080P_AAC`() {
    kotlin.run {
    val r =
        parse("[黑白字幕组]中年男的异世界网购生活 / Arafou Otoko no Isekai Tsuuhan Seikatsu [05] [webRip] [AVC-8bit 1080P AAC] [简日内嵌]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689416_DBD_Raws_Gekijou_Soushuuhen_Bocchi_the_Rock_21_SP_1080P_BDRip_HEVC_10bit_FLAC_AC3_MKV__21`() {
    kotlin.run {
    val r =
        parse("[DBD-Raws][孤独摇滚！剧场总集篇/Gekijou Soushuuhen Bocchi the Rock!][正片+SP][1080P][BDRip][HEVC-10bit][FLAC+AC3][MKV](劇場総集編ぼっち・ざ・ろっく!)")
    assertEquals("SP..SP", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689412_Bang_Dream_Ave_Mujica___07_CR_WebRip_AI2160p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[新沸羊羊制作] Bang Dream Ave Mujica - 07 [CR WebRip AI2160p HEVC-10bit AAC][官方简繁内封字幕]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689409_TensoRaws_BanG_Dream_21_Ave_Mujica_07_WEBRip_2160p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r = parse("[TensoRaws] BanG Dream! Ave Mujica [07] [WEBRip 2160p HEVC-10bit AAC]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689408_Ao_no_Hako_20_WEBrip_1080P`() {
    kotlin.run {
    val r = parse("【今晚月色真美】[青之箱 / アオのハコ / Ao no Hako][20][WEBrip][1080P][简日双语]")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689407_ANi_Rurouni_Kenshin___18_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[搬運][ANi] Rurouni Kenshin / 神劍闖江湖 ―明治劍客浪漫譚― 京都動亂 - 18 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689406_ANi_Anyway_Im_Falling_in_Love_with_You___06_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[搬運][ANi] Anyway Im Falling in Love with You / 終究，與你相戀。 - 06 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689405_ANi_TRILLION_GAME___19_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[搬運][ANi] TRILLION GAME / 一兆＄遊戲 - 19 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689404_ANi_Momentary_Lily_MOMENTARY_LILY___07_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[搬運][ANi] Momentary Lily / MOMENTARY LILY 剎那之花 - 07 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689403_ANi___06_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[搬運][ANi] 中年大叔轉生反派千金 - 06 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689402_ANi___06_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[搬運][ANi] 不幸職業【鑑定士】其實是最強的 - 06 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689401_ANi_Dr_STONE_S04_Dr_STONE___06_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[搬運][ANi] Dr STONE S04 / Dr.STONE 新石紀 第四季 - 06 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689400_ANi_BanG_Dream_21_Ave_Mujica___07_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[搬運][ANi] BanG Dream! Ave Mujica - 07 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689393_Skymoon_Raws_Akuyaku_Reijou_Tensei_Ojisan___06_ViuTV_WEB_DL_1080p_AVC_AAC`() {
    kotlin.run {
    val r =
        parse("[Skymoon-Raws] 中年大叔轉生反派千金 / Akuyaku Reijou Tensei Ojisan - 06 [ViuTV][WEB-DL][1080p][AVC AAC]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689392_01_Ishura_S2_06_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[异修罗 第二季 / Ishura S2][06][1080p][简日双语][招募翻译]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689391_Production_01_BanG_Dream_21_Ave_Mujica_07_1080p`() {
    kotlin.run {
    val r = parse("【喵萌Production】★01月新番★[BanG Dream! Ave Mujica][07][1080p][简日双语][招募翻译]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689390_06_GB_CN_HEVC_opus_1080p`() {
    kotlin.run {
    val r = parse("【极影字幕·毁片党】灰色：幻影扳机 第06集 GB_CN HEVC_opus 1080p")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689389_jibaketa_TVB_Dog_Signal___05_WEB_1920x1080_AVC_AAC_SRT_TVB_YUE_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][TVB粵語]愛犬訊號 / Dog Signal - 05 [粵語+內封繁體中文字幕][WEB 1920x1080 AVC AAC SRT TVB YUE CHT]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689387_DBD_4K_2160P_HDR_1080P_BanG_Dream_21_Ave_Mujica_07_WEB_DL_AAC_MKV`() {
    kotlin.run {
    val r =
        parse("[DBD制作组][4K(2160P)+HDR+1080P][BanG Dream! Ave Mujica][07][WEB-DL][简日双语内嵌][AAC][MKV]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689386_S1_BanG_Dream_21_Ave_Mujica_07_2160p_Bilibili_Web_DL_HDR_HEVC_AAC`() {
    kotlin.run {
    val r =
        parse("[S1百综字幕组]BanG Dream! Ave Mujica [07][2160p][Bilibili][Web-DL][HDR][HEVC_AAC][简日双语]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689385_LoliHouse_2_Watashi_no_Shiawase_na_Kekkon_S2___06_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[喵萌奶茶屋&LoliHouse] 我的幸福婚姻 2期 / Watashi no Shiawase na Kekkon S2 - 06 [WebRip 1080p HEVC-10bit AAC][简繁日内封字幕]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689384_DBD_Raws_ZZ_Mobile_Suit_Gundam_ZZ_01_47TV_1080P_BDRip_HEVC_10bit_FLAC_MKV_Kidou_Senshi_Gundam_ZZ_ZZ`() {
    kotlin.run {
    val r =
        parse("[DBD-Raws][机动战士高达ZZ/Mobile Suit Gundam ZZ][01-47TV全集+特典映像][1080P][BDRip][HEVC-10bit][简繁内封][FLAC][MKV](Kidou Senshi Gundam ZZ/機動戦士ガンダムZZ)")
    assertEquals("01..47", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689383_202_WEB_MKV_2160P`() {
    kotlin.run {
    val r = parse("[完美世界][第202集][国语中字][WEB-MKV][2160P]")
    assertEquals("202..202", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689382_Shakugan_no_Shana_I_04_BDRip_1080P_x264_AAC`() {
    kotlin.run {
    val r = parse("[晚街与灯][灼眼的夏娜 第一季_Shakugan no Shana I][04][BDRip_1080P_x264_AAC][简日内嵌]")
    assertEquals("04..04", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689381_Shakugan_no_Shana_I_04_BDRip_1080P_x264_AAC`() {
    kotlin.run {
    val r = parse("[晚街與燈][灼眼的夏娜 第一季_Shakugan no Shana I][04][BDRip_1080P_x264_AAC][繁日內嵌]")
    assertEquals("04..04", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689380_Re___10V2_CR_WebRip_AI2160p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r = parse("[新沸羊羊制作] Re：从零开始的异世界生活 第三季 - 10V2 [CR WebRip AI2160p HEVC-10bit AAC][官方简繁内封字幕]")
    assertEquals("10..10", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689377_DBD_Raws_Mairimashita_21_Iruma_kun_S2_01_21TV_1080P_BDRip_HEVC_10bit_FLAC_MKV_2`() {
    kotlin.run {
    val r =
        parse("[DBD-Raws][入间同学入魔了！ 第二季/Mairimashita! Iruma-kun S2][01-21TV全集][1080P][BDRip][HEVC-10bit][简繁外挂][FLAC][MKV](魔入りました！入間くん 第2シーズン)")
    assertEquals("01..21", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public fun `689364_OVA_I_s_Pure_2005_DVD720P`() {
    kotlin.run {
    val r = parse("OVA I''s Pure.2005.DVD720P.日语中字")
    assertEquals("OVA..OVA", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("720P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689363_LoliHouse_100_Hyakkano___15_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[千夏字幕组&喵萌奶茶屋&LoliHouse] 超超超超超喜歡你的100個女朋友 / Hyakkano - 15 [WebRip 1080p HEVC-10bit AAC][简繁日内封字幕]")
    assertEquals("15..15", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689362_ANi_Honey_Lemon_Soda___06_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[搬運][ANi] Honey Lemon Soda / 青春特調蜂蜜檸檬蘇打 - 06 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689361_ANi_Re___10_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[搬運][ANi] Re：從零開始的異世界生活 第三季 - 10 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("10..10", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689360_ANi___07_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r = parse("[搬運][ANi] 遲早是最強的鍊金術師？ - 07 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689358_GalaxyRailroad_888_GO_RUSH_21_21_Yu_Gi_Oh_21_GO_RUSH__21_21_144_720P_GB`() {
    kotlin.run {
    val r = parse("[GalaxyRailroad-888] 游戏王GO RUSH!! Yu-Gi-Oh! GO RUSH !! 144 720P [GB_简中]")
    assertEquals("144..144", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("720P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689357_01_2_Watashi_no_Shiawase_na_Kekkon_S2_06_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[我的幸福婚姻 2期 / Watashi no Shiawase na Kekkon S2][06][1080p][繁日雙語]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689356_01_2_Watashi_no_Shiawase_na_Kekkon_S2_06_1080p`() {
    kotlin.run {
    val r = parse("【喵萌奶茶屋】★01月新番★[我的幸福婚姻 2期 / Watashi no Shiawase na Kekkon S2][06][1080p][简日双语]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689355_Billion_Meta_Lab_Momentary_Lily_05_1080P`() {
    kotlin.run {
    val r = parse("[Billion Meta Lab] 曇花一現的百合 Momentary Lily [05][1080P][繁日內嵌][檢索：剎那之花]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689354_Billion_Meta_Lab_Momentary_Lily_05_1080P_HEVC_10bit`() {
    kotlin.run {
    val r =
        parse("[Billion Meta Lab] 昙花一现的百合 Momentary Lily [05][1080P][HEVC 10bit][简繁日内封][检索：刹那之花]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689353_Billion_Meta_Lab_Momentary_Lily_05_1080P`() {
    kotlin.run {
    val r = parse("[Billion Meta Lab] 昙花一现的百合 Momentary Lily [05][1080P][简日内嵌][检索：刹那之花]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689351_jibaketa_HOY_Pocket_Monsters_2023___34_WEB_1920x1080_AVC_AAC_PGS_SRT_iCABLE_YUE_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][HOY粵語]寶可夢 地平線 / Pocket Monsters (2023) - 34 [粵語+內封繁體中文字幕](WEB 1920x1080 AVC AAC PGS+SRT iCABLE YUE CHT)")
    assertEquals("34..34", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689350_____Ore_dake_Level_Up_na_Ken_S2_18_1080p_JaVer_2025_1`() {
    kotlin.run {
    val r =
        parse("[猎户压制部] 我独自升级 第二季 -起于暗影- / Ore dake Level Up na Ken S2 [18] [1080p] [繁日内嵌] [JaVer] [2025年1月番]")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689349_____Ore_dake_Level_Up_na_Ken_S2_18_1080p_JaVer_2025_1`() {
    kotlin.run {
    val r =
        parse("[猎户压制部] 我独自升级 第二季 -起于暗影- / Ore dake Level Up na Ken S2 [18] [1080p] [简日内嵌] [JaVer] [2025年1月番]")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689348_Skymoon_Raws_Re_Re_Zero_kara_Hajimeru_Isekai_Seikatsu_3rd_Season___10_ViuTV_WEB_DL_1080p_AVC_AAC`() {
    kotlin.run {
    val r =
        parse("[Skymoon-Raws] Re：從零開始的異世界生活 第三季 / Re：Zero kara Hajimeru Isekai Seikatsu 3rd Season - 10 [ViuTV][WEB-DL][1080p][AVC AAC]")
    assertEquals("10..10", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689347_Sentai_Red_Isekai_de_Boukensha_ni_Naru_05_1080p_2025_1`() {
    kotlin.run {
    val r =
        parse("[猎户手抄部] 红战士在异世界成了冒险者 / Sentai Red Isekai de Boukensha ni Naru [05] [1080p] [繁日内嵌] [2025年1月番]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689346_Sentai_Red_Isekai_de_Boukensha_ni_Naru_05_1080p_2025_1`() {
    kotlin.run {
    val r =
        parse("[猎户手抄部] 战队红战士在异世界当冒险者 / Sentai Red Isekai de Boukensha ni Naru [05] [1080p] [简日内嵌] [2025年1月番]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689345_Shangri_La_Frontier_S2_43_1080p_2024_10`() {
    kotlin.run {
    val r =
        parse("[猎户压制部] 香格里拉·开拓异境～粪作猎手挑战神作 第二季 / Shangri-La Frontier S2 [43] [1080p] [繁日内嵌] [2024年10月番]")
    assertEquals("43..43", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689344_Shangri_La_Frontier_S2_43_1080p_2024_10`() {
    kotlin.run {
    val r =
        parse("[猎户压制部] 香格里拉·开拓异境～粪作猎手挑战神作 第二季 / Shangri-La Frontier S2 [43] [1080p] [简日内嵌] [2024年10月番]")
    assertEquals("43..43", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `689343_LoliHouse_Mahoutsukai_no_Yakusoku___06_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 魔法使的约定 / Mahoutsukai no Yakusoku - 06 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689342_LoliHouse_Botsuraku_Yotei_no_Kizoku_dakedo_Hima_Datta_kara_Mahou_wo_Kiwametemita___06_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 虽然我是注定没落的贵族，闲来无事只好来深究魔法 / Botsuraku Yotei no Kizoku dakedo, Hima Datta kara Mahou wo Kiwametemita - 06 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689341_1_Kusuriya_no_Hitorigoto_29_1080P_MP4_BIG5_JP`() {
    kotlin.run {
    val r =
        parse("[漫貓字幕社&貓戀漢化組][1月新番][藥師少女的獨語][Kusuriya no Hitorigoto][29][1080P][MP4][BIG5&JP][繁日雙語]")
    assertEquals("29..29", r.episodeRange.toString())
    assertEquals("CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689340_1_Kusuriya_no_Hitorigoto_29_1080P_MP4_GB_JP`() {
    kotlin.run {
    val r =
        parse("[漫猫字幕社&猫恋汉化组][1月新番][药屋少女的呢喃][Kusuriya no Hitorigoto][29][1080P][MP4][GB&JP][简日双语]")
    assertEquals("29..29", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689339_Tower_of_God_S02E26_1080p_CR_WEB_DL_AAC2_0_H_264_DUAL_VARYG`() {
    kotlin.run {
    val r = parse("神之塔 Tower of God S02E26 1080p CR WEB-DL AAC2.0 H 264 DUAL-VARYG (多國語含中文) 第二季完")
    assertEquals("264..264", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689338_Tower_of_God_S02E25_1080p_CR_WEB_DL_AAC2_0_H_264_DUAL_VARYG`() {
    kotlin.run {
    val r = parse("神之塔 Tower of God S02E25 1080p CR WEB-DL AAC2.0 H 264 DUAL-VARYG (多國語含中文)")
    assertEquals("264..264", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689337_Tower_of_God_S02E24_1080p_CR_WEB_DL_AAC2_0_H_264_DUAL_VARYG`() {
    kotlin.run {
    val r = parse("神之塔 Tower of God S02E24 1080p CR WEB-DL AAC2.0 H 264 DUAL-VARYG (多國語含中文)")
    assertEquals("264..264", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689336_Tower_of_God_S02E23_1080p_CR_WEB_DL_AAC2_0_H_264_DUAL_VARYG`() {
    kotlin.run {
    val r = parse("神之塔 Tower of God S02E23 1080p CR WEB-DL AAC2.0 H 264 DUAL-VARYG (多國語含中文)")
    assertEquals("264..264", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689335_Crayonshinchan_1270_2025_02_08_AVC_1080P_GB_JP_MP4`() {
    kotlin.run {
    val r = parse("[梦蓝字幕组]Crayonshinchan 蜡笔小新[1270][2025.02.08][AVC][1080P][GB_JP][MP4]")
    assertEquals("1270..1270", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689334_Crayonshinchan_1269_2025_02_01_AVC_1080P_GB_JP_MP4`() {
    kotlin.run {
    val r = parse("[梦蓝字幕组]Crayonshinchan 蜡笔小新[1269][2025.02.01][AVC][1080P][GB_JP][MP4]")
    assertEquals("1269..1269", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689333_New_Doraemon_A_848_2025_02_01_AVC_1080P_GB_JP_MP4`() {
    kotlin.run {
    val r = parse("[梦蓝字幕组]New Doraemon 哆啦A梦新番[848][2025.02.01][AVC][1080P][GB_JP][MP4]")
    assertEquals("848..848", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689332_LoliHouse_Around_40_Otoko_no_Isekai_Tsuuhan___04_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 中年男的异世界网购生活 / Around 40 Otoko no Isekai Tsuuhan - 04 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("04..04", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `689331_LoliHouse_Sararyman_ga_Isekai_ni_Ittara_Shitennou_ni_Natta_Hanashi___07_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 平凡上班族到异世界当上了四天王的故事 / Sararyman ga Isekai ni Ittara Shitennou ni Natta Hanashi - 07 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689330_28_1080p`() {
    kotlin.run {
    val r = parse("[萌樱字幕组] 药屋少女的呢喃 [28][1080p][简繁日内封]")
    assertEquals("28..28", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689329_Chibi_Maruko_chan_II_1466_2025_02_09_GB_JP_1080P_MP4`() {
    kotlin.run {
    val r =
        parse("[夜莺家族][樱桃小丸子第二期(Chibi Maruko-chan II)][1466]富子想赢躲避球比赛&小丸子擅长安慰他人[2025.02.09][GB_JP][1080P][MP4]")
    assertEquals("1466..1466", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689325_VCB_Studio_FLOPS_LOVE_FLOPS_Renai_Flops_10_bit_1080p_HEVC_BDRip_Fin`() {
    kotlin.run {
    val r =
        parse("[喵萌奶茶屋&VCB-Studio] 恋爱FLOPS / LOVE FLOPS / Renai Flops / 恋愛フロップス 10-bit 1080p HEVC BDRip [Fin]")
    assertEquals("S?", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689324_LoliHouse_Kinomi_Master___07_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 最弱技能《果实大师》 ～关于能无限食用技能果实（吃了就会死）这件事～ / Kinomi Master - 07 [WebRip 1080p HEVC-10bit AAC][无字幕]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `689323_LoliHouse_Amagami_san_Chi_no_Enmusubi___18_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[LoliHouse] 结缘甘神神社 / Amagami-san Chi no Enmusubi - 18 [WebRip 1080p HEVC-10bit AAC][简繁内封字幕]")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689322_S___07_AMZN_WebRip_AI2160p_HEVC_10bit_E_AC_3`() {
    kotlin.run {
    val r =
        parse("[新沸羊羊制作] S级怪兽《贝希摩斯》被误认成小猫，成为精灵女孩的骑士(宠物)一起生活 - 07 [AMZN WebRip AI2160p HEVC-10bit E-AC-3][官方简繁内封字幕]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("4K", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `689321_ANi_Hana_wa_Saku_Shura_no_Gotoku___06_1080P_Baha_WEB_DL_AAC_AVC_CHT_MP4`() {
    kotlin.run {
    val r =
        parse("[搬運][ANi] Hana wa Saku Shura no Gotoku / 群花綻放、彷如修羅 - 06 [1080P][Baha][WEB-DL][AAC AVC][CHT][MP4]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }
}

// @formatter:on
