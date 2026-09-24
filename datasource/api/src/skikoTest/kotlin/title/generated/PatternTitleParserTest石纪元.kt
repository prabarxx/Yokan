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
 * 原名: `石纪元`
 * 数据源: `dmhy`
 *
 * 由 `test-codegen` 的 `GenerateTests.kt` 生成, 不要手动修改!
 * 如果你优化了解析器, 这些 test 可能会失败, 请检查是否它是因为以前解析错误而现在解析正确了. 
 * 如果是, 请更新测试数据: 执行 `GenerateTests.kt`.
 */
public class PatternTitleParserTest石纪元 : PatternBasedTitleParserTestSuite() {
  @Test
  public fun `690822_Dr_STONE_Science_Future_05_1080p_2025_1`() {
    kotlin.run {
    val r = parse("[猎户压制部] 石纪元 第四季 科学与未来 / Dr.STONE：Science Future [05] [1080p] [简日内嵌] [2025年1月番]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
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
  public
      fun `689075_DBD_Raws_Stone_Wars_Dr_Stone_Stone_Wars_01_11TV_1080P_BDRip_HEVC_10bit_FLAC_MKV_Dr_Stone_S2_Stone_Wars`() {
    kotlin.run {
    val r =
        parse("[DBD-Raws][新石纪 Stone Wars/Dr. Stone Stone Wars][01-11TV全集][1080P][BDRip][HEVC-10bit][简繁外挂][FLAC][MKV](石纪元 第二季/Dr. Stone S2/ドクターストーン Stone Wars)")
    assertEquals("01..11", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public
      fun `688856_DBD_Raws_New_World_Dr_Stone_New_World_01_22TV_SP_1080P_BDRip_HEVC_10bit_FLAC_MKV_Dr_Stone_S3_New_World`() {
    kotlin.run {
    val r =
        parse("[DBD-Raws][新石纪 New World/Dr. Stone New World][01-22TV全集+SP][1080P][BDRip][HEVC-10bit][简繁外挂][FLAC][MKV](石纪元 第三季/Dr. Stone S3/ドクターストーン New World)")
    assertEquals("01..22+SP", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public fun `688817_Dr_STONE_SCIENCE_FUTURE_01_CR_WebRip_1080p_HEVC_AAC_S4`() {
    kotlin.run {
    val r =
        parse("[芝士动物朋友] 石纪元 科学与未来 / Dr.STONE SCIENCE FUTURE [01][CR-WebRip][1080p][HEVC+AAC][简繁内封]（新石纪 第四季 S4）")
    assertEquals("01..01", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `679339_jibaketa_Dr_Stone_New_World___22_END_WEB_1920x1080_AVC_AACx2_PGS_SRT_Ani_One_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][代理商粵語]Dr.Stone New World 新石紀 / 石纪元 第三季 新世界 - 22 END [粵日雙語+內封繁體中文雙字幕] (WEB 1920x1080 AVC AACx2 PGS+SRT Ani-One CHT)")
    assertEquals("22..22", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `678927_jibaketa_Dr_Stone_New_World___21_WEB_1920x1080_AVC_AACx2_PGS_SRT_Ani_One_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][代理商粵語]Dr.Stone New World 新石紀 / 石纪元 第三季 新世界 - 21 [粵日雙語+內封繁體中文雙字幕] (WEB 1920x1080 AVC AACx2 PGS+SRT Ani-One CHT)")
    assertEquals("21..21", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `678485_jibaketa_Dr_Stone_New_World___20_WEB_1920x1080_AVC_AACx2_PGS_SRT_Ani_One_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][代理商粵語]Dr.Stone New World 新石紀 / 石纪元 第三季 新世界 - 20 [粵日雙語+內封繁體中文雙字幕] (WEB 1920x1080 AVC AACx2 PGS+SRT Ani-One CHT)")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `678132_jibaketa_Dr_Stone_New_World___19_WEB_1920x1080_AVC_AACx2_PGS_SRT_Ani_One_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][代理商粵語]Dr.Stone New World 新石紀 / 石纪元 第三季 新世界 - 19 [粵日雙語+內封繁體中文雙字幕] (WEB 1920x1080 AVC AACx2 PGS+SRT Ani-One CHT)")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `677722_jibaketa_Dr_Stone_New_World___18_WEB_1920x1080_AVC_AACx2_PGS_SRT_Ani_One_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][代理商粵語]Dr.Stone New World 新石紀 / 石纪元 第三季 新世界 - 18 [粵日雙語+內封繁體中文雙字幕] (WEB 1920x1080 AVC AACx2 PGS+SRT Ani-One CHT)")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `677229_jibaketa_Dr_Stone_New_World___17_WEB_1920x1080_AVC_AACx2_PGS_SRT_Ani_One_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][代理商粵語]Dr.Stone New World 新石紀 / 石纪元 第三季 新世界 - 17 [粵日雙語+內封繁體中文雙字幕] (WEB 1920x1080 AVC AACx2 PGS+SRT Ani-One CHT)")
    assertEquals("17..17", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `674728_jibaketa_Dr_Stone_New_World___16_WEB_1920x1080_AVC_AACx2_PGS_SRT_Ani_One_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][代理商粵語]Dr.Stone New World 新石紀 / 石纪元 第三季 新世界 - 16 [粵日雙語+內封繁體中文雙字幕] (WEB 1920x1080 AVC AACx2 PGS+SRT Ani-One CHT)")
    assertEquals("16..16", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `674226_jibaketa_Dr_Stone_New_World___15_WEB_1920x1080_AVC_AACx2_PGS_SRT_Ani_One_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][代理商粵語]Dr.Stone New World 新石紀 / 石纪元 第三季 新世界 - 15 [粵日雙語+內封繁體中文雙字幕] (WEB 1920x1080 AVC AACx2 PGS+SRT Ani-One CHT)")
    assertEquals("15..15", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `673771_jibaketa_Dr_Stone_New_World___14_WEB_1920x1080_AVC_AACx2_PGS_SRT_Ani_One_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][代理商粵語]Dr.Stone New World 新石紀 / 石纪元 第三季 新世界 - 14 [粵日雙語+內封繁體中文雙字幕] (WEB 1920x1080 AVC AACx2 PGS+SRT Ani-One CHT)")
    assertEquals("14..14", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `673207_jibaketa_Dr_Stone_New_World___13_WEB_1920x1080_AVC_AACx2_PGS_SRT_Ani_One_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][代理商粵語]Dr.Stone New World 新石紀 / 石纪元 第三季 新世界 - 13 [粵日雙語+內封繁體中文雙字幕] (WEB 1920x1080 AVC AACx2 PGS+SRT Ani-One CHT)")
    assertEquals("13..13", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `672727_jibaketa_Dr_Stone_New_World___12_WEB_1920x1080_AVC_AACx2_PGS_SRT_Ani_One_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][代理商粵語]Dr.Stone New World 新石紀 / 石纪元 第三季 新世界 - 12 [粵日雙語+內封繁體中文雙字幕] (WEB 1920x1080 AVC AACx2 PGS+SRT Ani-One CHT)")
    assertEquals("12..12", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `672230_jibaketa_Dr_Stone_New_World___11_BD_1920x1080_x264_AACx2_PGS_SRT_Ani_One_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&壓制][代理商粵語]Dr.Stone New World 新石紀 / 石纪元 第三季 新世界 - 11 [粵日雙語+內封繁體中文雙字幕] (BD 1920x1080 x264 AACx2 PGS+SRT Ani-One CHT)")
    assertEquals("11..11", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `671268_jibaketa_Dr_Stone_New_World___10_BD_1920x1080_x264_AACx2_PGS_SRT_Ani_One_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&壓制][代理商粵語]Dr.Stone New World 新石紀 / 石纪元 第三季 新世界 - 10 [粵日雙語+內封繁體中文雙字幕] (BD 1920x1080 x264 AACx2 PGS+SRT Ani-One CHT)")
    assertEquals("10..10", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `670745_jibaketa_Dr_Stone_New_World___09_BD_1920x1080_x264_AACx2_PGS_SRT_Ani_One_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&壓制][代理商粵語]Dr.Stone New World 新石紀 / 石纪元 第三季 新世界 - 09 [粵日雙語+內封繁體中文雙字幕] (BD 1920x1080 x264 AACx2 PGS+SRT Ani-One CHT)")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `670200_jibaketa_Dr_Stone_New_World___08_BD_1920x1080_x264_AACx2_PGS_SRT_Ani_One_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&壓制][代理商粵語]Dr.Stone New World 新石紀 / 石纪元 第三季 新世界 - 08 [粵日雙語+內封繁體中文雙字幕] (BD 1920x1080 x264 AACx2 PGS+SRT Ani-One CHT)")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `669435_jibaketa_Dr_Stone_New_World___07_BD_1920x1080_x264_AACx2_PGS_SRT_Ani_One_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&壓制][代理商粵語]Dr.Stone New World 新石紀 / 石纪元 第三季 新世界 - 07 [粵日雙語+內封繁體中文雙字幕] (BD 1920x1080 x264 AACx2 PGS+SRT Ani-One CHT)")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `668935_jibaketa_Dr_Stone_New_World___06_BD_1920x1080_x264_AACx2_PGS_SRT_Ani_One_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&壓制][代理商粵語]Dr.Stone New World 新石紀 / 石纪元 第三季 新世界 - 06 [粵日雙語+內封繁體中文雙字幕] (BD 1920x1080 x264 AACx2 PGS+SRT Ani-One CHT)")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `668405_jibaketa_Dr_Stone_New_World___05_BD_1920x1080_x264_AACx2_PGS_SRT_Ani_One_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&壓制][代理商粵語]Dr.Stone New World 新石紀 / 石纪元 第三季 新世界 - 05 [粵日雙語+內封繁體中文雙字幕] (BD 1920x1080 x264 AACx2 PGS+SRT Ani-One CHT)")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `667821_jibaketa_Dr_Stone_New_World___04_BD_1920x1080_x264_AACx2_PGS_SRT_Ani_One_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&壓制][代理商粵語]Dr.Stone New World 新石紀 / 石纪元 第三季 新世界 - 04 [粵日雙語+內封繁體中文雙字幕] (BD 1920x1080 x264 AACx2 PGS+SRT Ani-One CHT)")
    assertEquals("04..04", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `667820_jibaketa_Dr_Stone_New_World___03_BD_1920x1080_x264_AACx2_PGS_SRT_Ani_One_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&壓制][代理商粵語]Dr.Stone New World 新石紀 / 石纪元 第三季 新世界 - 03 [粵日雙語+內封繁體中文雙字幕] (BD 1920x1080 x264 AACx2 PGS+SRT Ani-One CHT)")
    assertEquals("03..03", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `666366_jibaketa_Dr_Stone_New_World___02_BD_1920x1080_x264_AACx2_PGS_SRT_Ani_One_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&壓制][代理商粵語]Dr.Stone New World 新石紀 / 石纪元 第三季 新世界 - 02 [粵日雙語+內封繁體中文雙字幕] (BD 1920x1080 x264 AACx2 PGS+SRT Ani-One CHT)")
    assertEquals("02..02", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `665820_jibaketa_Dr_Stone_New_World___01_BD_1920x1080_x264_AACx2_PGS_SRT_Ani_One_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&壓制][代理商粵語]Dr.Stone New World 新石紀 / 石纪元 第三季 新世界 - 01 [粵日雙語+內封繁體中文雙字幕] (BD 1920x1080 x264 AACx2 PGS+SRT Ani-One CHT)")
    assertEquals("01..01", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `665454_jibaketa_Dr_Stone_Ryuusui_BD_1920x1080_x264_AACx2_PGS_SRT_Ani_One_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&壓制][代理商粵語]Dr.Stone Ryuusui 新石紀 / 石纪元 龍水 [粵日雙語+內封繁體中文雙字幕] (BD 1920x1080 x264 AACx2 PGS+SRT Ani-One CHT)")
    assertEquals("S?", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `659517_Dr_STONE_New_World_12_22_WebRip_1080p_2023_10`() {
    kotlin.run {
    val r =
        parse("[猎户压制部] 石纪元 第三季 下半 / Dr.STONE：New World [12-22] [WebRip] [1080p] [简日内嵌] [2023年10月番]")
    assertEquals("12..22", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `659248_Dr_STONE_New_World_22_END_1080p_2023_10`() {
    kotlin.run {
    val r = parse("[猎户压制部] 石纪元 第三季 下半 / Dr.STONE：New World [22] [END] [1080p] [简日内嵌] [2023年10月番]")
    assertEquals("22..22", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `658708_Dr_STONE_New_World_21_1080p_2023_10`() {
    kotlin.run {
    val r = parse("[猎户压制部] 石纪元 第三季 下半 / Dr.STONE：New World [21] [1080p] [简日内嵌] [2023年10月番]")
    assertEquals("21..21", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `658151_Dr_STONE_New_World_20_1080p_2023_10`() {
    kotlin.run {
    val r = parse("[猎户压制部] 石纪元 第三季 下半 / Dr.STONE：New World [20] [1080p] [简日内嵌] [2023年10月番]")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `657480_Dr_STONE_New_World_19_1080p_2023_10`() {
    kotlin.run {
    val r = parse("[猎户压制部] 石纪元 第三季 下半 / Dr.STONE：New World [19] [1080p] [简日内嵌] [2023年10月番]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `657082_Dr_STONE_New_World_18_1080p_2023_10`() {
    kotlin.run {
    val r = parse("[猎户压制部] 石纪元 第三季 下半 / Dr.STONE：New World [18] [1080p] [简日内嵌] [2023年10月番]")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `656749_Dr_STONE_New_World_17_1080p_2023_4`() {
    kotlin.run {
    val r = parse("[猎户压制部] 石纪元 第三季 下半 / Dr.STONE：New World [17] [1080p] [简日内嵌] [2023年4月番]")
    assertEquals("17..17", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `656416_Dr_STONE_New_World_16_1080p_2023_4`() {
    kotlin.run {
    val r = parse("[猎户压制部] 石纪元 第三季 下半 / Dr.STONE：New World [16] [1080p] [简日内嵌] [2023年4月番]")
    assertEquals("16..16", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `655803_Dr_STONE_New_World_15_1080p_2023_4`() {
    kotlin.run {
    val r = parse("[猎户压制部] 石纪元 第三季 下半 / Dr.STONE：New World [15] [1080p] [简日内嵌] [2023年4月番]")
    assertEquals("15..15", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `655282_Dr_STONE_New_World_14_1080p_2023_4`() {
    kotlin.run {
    val r = parse("[猎户压制部] 石纪元 第三季 下半 / Dr.STONE：New World [14] [1080p] [简日内嵌] [2023年4月番]")
    assertEquals("14..14", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `654897_Dr_STONE_New_World_13_1080p_2023_4`() {
    kotlin.run {
    val r = parse("[猎户压制部] 石纪元 第三季 下半 / Dr.STONE：New World [13] [1080p] [简日内嵌] [2023年4月番]")
    assertEquals("13..13", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `654713_Dr_STONE_New_World_12_1080p_2023_4`() {
    kotlin.run {
    val r = parse("[猎户压制部] 石纪元 第三季 下半 / Dr.STONE：New World [12] [1080p] [简日内嵌] [2023年4月番]")
    assertEquals("12..12", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `644072_Dr_STONE_New_World_01_11_1080p_2023_4`() {
    kotlin.run {
    val r = parse("[猎户手抄部] 石纪元 第三季 Dr.STONE：New World [01-11] [合集] [1080p] [简日内嵌] [2023年4月番]")
    assertEquals("01..11", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `643782_LoliHouse_Dr_STONE_S3_01_11_WebRip_1080p_HEVC_10bit_AAC_Fin`() {
    kotlin.run {
    val r =
        parse("[豌豆字幕组&风之圣殿字幕组&LoliHouse] 新石纪 第三季 / 石纪元 第三季 / Dr.STONE S3 [01-11 合集][WebRip 1080p HEVC-10bit AAC][简繁外挂字幕][Fin]")
    assertEquals("01..11", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public fun `643763_04_Dr_STONE_01_11_1080P_MP4`() {
    kotlin.run {
    val r = parse("【豌豆字幕组&风之圣殿字幕组】★04月新番[Dr.STONE 新石纪 / 石纪元 第三季][01-11][合集][简体][1080P][MP4]")
    assertEquals("01..11", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `643762_04_Dr_STONE_01_11_1080P_MP4`() {
    kotlin.run {
    val r = parse("【豌豆字幕組&風之聖殿字幕組】★04月新番[Dr.STONE 新石紀 / 石紀元 第三季][01-11][合集][繁體][1080P][MP4]")
    assertEquals("01..11", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `643158_Dr_STONE_New_World_11_1080p_2023_4`() {
    kotlin.run {
    val r = parse("[猎户手抄部] 石纪元 第三季 Dr.STONE：New World [11] [1080p] [简繁日内封] [2023年4月番]")
    assertEquals("11..11", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `643157_Dr_STONE_New_World_11_1080p_2023_4`() {
    kotlin.run {
    val r = parse("[猎户手抄部] 石纪元 第三季 Dr.STONE：New World [11] [1080p] [简日内嵌] [2023年4月番]")
    assertEquals("11..11", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `643093_LoliHouse_Dr_STONE_S3___11_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[豌豆字幕组&风之圣殿字幕组&LoliHouse] 新石纪 第三季 / 石纪元 第三季 / Dr.STONE S3 - 11 [WebRip 1080p HEVC-10bit AAC][简繁外挂字幕]")
    assertEquals("11..11", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public fun `643048_04_Dr_STONE_11_1080P_MP4`() {
    kotlin.run {
    val r = parse("【豌豆字幕组&风之圣殿字幕组】★04月新番[Dr.STONE 新石纪 / 石纪元 第三季][11][简体][1080P][MP4]")
    assertEquals("11..11", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `643047_04_Dr_STONE_11_1080P_MP4`() {
    kotlin.run {
    val r = parse("【豌豆字幕組&風之聖殿字幕組】★04月新番[Dr.STONE 新石紀 / 石紀元 第三季][11][繁體][1080P][MP4]")
    assertEquals("11..11", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `642575_Dr_STONE_New_World_10_1080p_2023_4`() {
    kotlin.run {
    val r = parse("[猎户手抄部] 石纪元 第三季 Dr.STONE：New World [10] [1080p] [简繁日内封] [2023年4月番]")
    assertEquals("10..10", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `642574_Dr_STONE_New_World_10_1080p_2023_4`() {
    kotlin.run {
    val r = parse("[猎户手抄部] 石纪元 第三季 Dr.STONE：New World [10] [1080p] [简日内嵌] [2023年4月番]")
    assertEquals("10..10", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `642509_LoliHouse_Dr_STONE_S3___10_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[豌豆字幕组&风之圣殿字幕组&LoliHouse] 新石纪 第三季 / 石纪元 第三季 / Dr.STONE S3 - 10 [WebRip 1080p HEVC-10bit AAC][简繁外挂字幕]")
    assertEquals("10..10", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public fun `642463_04_Dr_STONE_10_1080P_MP4`() {
    kotlin.run {
    val r = parse("【豌豆字幕组&风之圣殿字幕组】★04月新番[Dr.STONE 新石纪 / 石纪元 第三季][10][简体][1080P][MP4]")
    assertEquals("10..10", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `642462_04_Dr_STONE_10_1080P_MP4`() {
    kotlin.run {
    val r = parse("【豌豆字幕組&風之聖殿字幕組】★04月新番[Dr.STONE 新石紀 / 石紀元 第三季][10][繁體][1080P][MP4]")
    assertEquals("10..10", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `642014_Dr_STONE_New_World_09_1080p_2023_4`() {
    kotlin.run {
    val r = parse("[猎户手抄部] 石纪元 第三季 Dr.STONE：New World [09] [1080p] [简繁日内封] [2023年4月番]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `642013_Dr_STONE_New_World_09_1080p_2023_4`() {
    kotlin.run {
    val r = parse("[猎户手抄部] 石纪元 第三季 Dr.STONE：New World [09] [1080p] [简日内嵌] [2023年4月番]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `641868_LoliHouse_Dr_STONE_S3___09_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[豌豆字幕组&风之圣殿字幕组&LoliHouse] 新石纪 第三季 / 石纪元 第三季 / Dr.STONE S3 - 09 [WebRip 1080p HEVC-10bit AAC][简繁外挂字幕]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public fun `641865_04_Dr_STONE_09_1080P_MP4`() {
    kotlin.run {
    val r = parse("【豌豆字幕组&风之圣殿字幕组】★04月新番[Dr.STONE 新石纪 / 石纪元 第三季][09][简体][1080P][MP4]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `641864_04_Dr_STONE_09_1080P_MP4`() {
    kotlin.run {
    val r = parse("【豌豆字幕組&風之聖殿字幕組】★04月新番[Dr.STONE 新石紀 / 石紀元 第三季][09][繁體][1080P][MP4]")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `641499_Dr_STONE_New_World_08_1080p_2023_4`() {
    kotlin.run {
    val r = parse("[猎户手抄部] 石纪元 第三季 Dr.STONE：New World [08] [1080p] [简繁日内封] [2023年4月番]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `641500_Dr_STONE_New_World_08_1080p_2023_4`() {
    kotlin.run {
    val r = parse("[猎户手抄部] 石纪元 第三季 Dr.STONE：New World [08] [1080p] [简日内嵌] [2023年4月番]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `641365_LoliHouse_Dr_STONE_S3___08_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[豌豆字幕组&风之圣殿字幕组&LoliHouse] 新石纪 第三季 / 石纪元 第三季 / Dr.STONE S3 - 08 [WebRip 1080p HEVC-10bit AAC][简繁外挂字幕]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public fun `641286_04_Dr_STONE_08_1080P_MP4`() {
    kotlin.run {
    val r = parse("【豌豆字幕组&风之圣殿字幕组】★04月新番[Dr.STONE 新石纪 / 石纪元 第三季][08][简体][1080P][MP4]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `641285_04_Dr_STONE_08_1080P_MP4`() {
    kotlin.run {
    val r = parse("【豌豆字幕組&風之聖殿字幕組】★04月新番[Dr.STONE 新石紀 / 石紀元 第三季][08][繁體][1080P][MP4]")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `640857_Dr_STONE_New_World_07_1080p_2023_4`() {
    kotlin.run {
    val r = parse("[猎户手抄部] 石纪元 第三季 Dr.STONE：New World [07] [1080p] [简繁日内封] [2023年4月番]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `640856_Dr_STONE_New_World_07_1080p_2023_4`() {
    kotlin.run {
    val r = parse("[猎户手抄部] 石纪元 第三季 Dr.STONE：New World [07] [1080p] [简日内嵌] [2023年4月番]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `640661_LoliHouse_Dr_STONE_S3___07_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[豌豆字幕组&风之圣殿字幕组&LoliHouse] 新石纪 第三季 / 石纪元 第三季 / Dr.STONE S3 - 07 [WebRip 1080p HEVC-10bit AAC][简繁外挂字幕]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public fun `640615_04_Dr_STONE_07_1080P_MP4`() {
    kotlin.run {
    val r = parse("【豌豆字幕组&风之圣殿字幕组】★04月新番[Dr.STONE 新石纪 / 石纪元 第三季][07][简体][1080P][MP4]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `640614_04_Dr_STONE_07_1080P_MP4`() {
    kotlin.run {
    val r = parse("【豌豆字幕組&風之聖殿字幕組】★04月新番[Dr.STONE 新石紀 / 石紀元 第三季][07][繁體][1080P][MP4]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `640411_Dr_STONE_New_World_06_1080p_2023_4`() {
    kotlin.run {
    val r = parse("[猎户手抄部] 石纪元 第三季 Dr.STONE：New World [06] [1080p] [简繁日内封] [2023年4月番]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `640410_Dr_STONE_New_World_06_1080p_2023_4`() {
    kotlin.run {
    val r = parse("[猎户手抄部] 石纪元 第三季 Dr.STONE：New World [06] [1080p] [简日内嵌] [2023年4月番]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `640034_LoliHouse_Dr_STONE_S3___06_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[豌豆字幕组&风之圣殿字幕组&LoliHouse] 新石纪 第三季 / 石纪元 第三季 / Dr.STONE S3 - 06 [WebRip 1080p HEVC-10bit AAC][简繁外挂字幕]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public fun `640007_04_Dr_STONE_06_1080P_MP4`() {
    kotlin.run {
    val r = parse("【豌豆字幕组&风之圣殿字幕组】★04月新番[Dr.STONE 新石纪 / 石纪元 第三季][06][简体][1080P][MP4]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `640006_04_Dr_STONE_06_1080P_MP4`() {
    kotlin.run {
    val r = parse("【豌豆字幕組&風之聖殿字幕組】★04月新番[Dr.STONE 新石紀 / 石紀元 第三季][06][繁體][1080P][MP4]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `639600_Dr_STONE_New_World_05_1080p_2023_4`() {
    kotlin.run {
    val r = parse("[猎户手抄部] 石纪元 第三季 Dr.STONE：New World [05] [1080p] [简繁日内封] [2023年4月番]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `639599_Dr_STONE_New_World_05_1080p_2023_4`() {
    kotlin.run {
    val r = parse("[猎户手抄部] 石纪元 第三季 Dr.STONE：New World [05] [1080p] [简日内嵌] [2023年4月番]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `639590_Dr_STONE_New_World_04_1080p_2023_4`() {
    kotlin.run {
    val r = parse("[猎户手抄部] 石纪元 第三季 Dr.STONE：New World [04] [1080p] [简繁日内封] [2023年4月番]")
    assertEquals("04..04", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `639589_Dr_STONE_New_World_04_1080p_2023_4`() {
    kotlin.run {
    val r = parse("[猎户手抄部] 石纪元 第三季 Dr.STONE：New World [04] [1080p] [简日内嵌] [2023年4月番]")
    assertEquals("04..04", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `639296_LoliHouse_Dr_STONE_S3___05_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[豌豆字幕组&风之圣殿字幕组&LoliHouse] 新石纪 第三季 / 石纪元 第三季 / Dr.STONE S3 - 05 [WebRip 1080p HEVC-10bit AAC][简繁外挂字幕]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public fun `639285_04_Dr_STONE_05_1080P_MP4`() {
    kotlin.run {
    val r = parse("【豌豆字幕组&风之圣殿字幕组】★04月新番[Dr.STONE 新石纪 / 石纪元 第三季][05][简体][1080P][MP4]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `639284_04_Dr_STONE_05_1080P_MP4`() {
    kotlin.run {
    val r = parse("【豌豆字幕組&風之聖殿字幕組】★04月新番[Dr.STONE 新石紀 / 石紀元 第三季][05][繁體][1080P][MP4]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `638641_LoliHouse_Dr_STONE_S3___04_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[豌豆字幕组&风之圣殿字幕组&LoliHouse] 新石纪 第三季 / 石纪元 第三季 / Dr.STONE S3 - 04 [WebRip 1080p HEVC-10bit AAC][简繁外挂字幕]")
    assertEquals("04..04", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public fun `638637_04_Dr_STONE_04_1080P_MP4`() {
    kotlin.run {
    val r = parse("【豌豆字幕组&风之圣殿字幕组】★04月新番[Dr.STONE 新石纪 / 石纪元 第三季][04][简体][1080P][MP4]")
    assertEquals("04..04", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `638636_04_Dr_STONE_04_1080P_MP4`() {
    kotlin.run {
    val r = parse("【豌豆字幕組&風之聖殿字幕組】★04月新番[Dr.STONE 新石紀 / 石紀元 第三季][04][繁體][1080P][MP4]")
    assertEquals("04..04", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `638317_Dr_STONE_New_World_03_1080p_2023_4`() {
    kotlin.run {
    val r = parse("[猎户手抄部] 石纪元 第三季 Dr.STONE：New World [03] [1080p] [简繁日内封] [2023年4月番]")
    assertEquals("03..03", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `638303_Dr_STONE_New_World_03_1080p_2023_4`() {
    kotlin.run {
    val r = parse("[猎户手抄部] 石纪元 第三季 Dr.STONE：New World [03] [1080p] [简日内嵌] [2023年4月番]")
    assertEquals("03..03", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `638032_LoliHouse_Dr_STONE_S3___03_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[豌豆字幕组&风之圣殿字幕组&LoliHouse] 新石纪 第三季 / 石纪元 第三季 / Dr.STONE S3 - 03 [WebRip 1080p HEVC-10bit AAC][简繁外挂字幕]")
    assertEquals("03..03", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public fun `637942_04_Dr_STONE_03_1080P_MP4`() {
    kotlin.run {
    val r = parse("【豌豆字幕组&风之圣殿字幕组】★04月新番[Dr.STONE 新石纪 / 石纪元 第三季][03][简体][1080P][MP4]")
    assertEquals("03..03", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `637941_04_Dr_STONE_03_1080P_MP4`() {
    kotlin.run {
    val r = parse("【豌豆字幕組&風之聖殿字幕組】★04月新番[Dr.STONE 新石紀 / 石紀元 第三季][03][繁體][1080P][MP4]")
    assertEquals("03..03", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `637505_Dr_STONE_New_World_02_1080p_2023_4`() {
    kotlin.run {
    val r = parse("[猎户手抄部] 石纪元 第三季 Dr.STONE：New World [02] [1080p] [简繁日内封] [2023年4月番]")
    assertEquals("02..02", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `637504_Dr_STONE_New_World_02_1080p_2023_4`() {
    kotlin.run {
    val r = parse("[猎户手抄部] 石纪元 第三季 Dr.STONE：New World [02] [1080p] [简日内嵌] [2023年4月番]")
    assertEquals("02..02", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `637234_LoliHouse_Dr_STONE_S3___02_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[豌豆字幕组&风之圣殿字幕组&LoliHouse] 新石纪 第三季 / 石纪元 第三季 / Dr.STONE S3 - 02 [WebRip 1080p HEVC-10bit AAC][简繁外挂字幕]")
    assertEquals("02..02", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public fun `637208_04_Dr_STONE_02_1080P_MP4`() {
    kotlin.run {
    val r = parse("【豌豆字幕组&风之圣殿字幕组】★04月新番[Dr.STONE 新石纪 / 石纪元 第三季][02][简体][1080P][MP4]")
    assertEquals("02..02", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `637207_04_Dr_STONE_02_1080P_MP4`() {
    kotlin.run {
    val r = parse("【豌豆字幕組&風之聖殿字幕組】★04月新番[Dr.STONE 新石紀 / 石紀元 第三季][02][繁體][1080P][MP4]")
    assertEquals("02..02", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `636740_Dr_STONE`() {
    kotlin.run {
    val r = parse("石纪元 新石纪 Dr.STONE S02")
    assertEquals("S2", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `636739_Dr_STONE_S01`() {
    kotlin.run {
    val r = parse("石纪元 新石纪 Dr.STONE S01")
    assertEquals("S1", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `636738_Dr_STONE`() {
    kotlin.run {
    val r = parse("石纪元 新石纪 Dr.STONE S01+S02")
    assertEquals("S1+S2", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("null", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `636671_Dr_STONE_New_World_01_1080p_2023_4`() {
    kotlin.run {
    val r = parse("[猎户手抄部] 石纪元 第三季 Dr.STONE：New World [01] [1080p] [简繁日内封] [2023年4月番]")
    assertEquals("01..01", r.episodeRange.toString())
    assertEquals("CHS, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `636670_Dr_STONE_New_World_01_1080p_2023_4`() {
    kotlin.run {
    val r = parse("[猎户手抄部] 石纪元 第三季 Dr.STONE：New World [01] [1080p] [简日内嵌] [2023年4月番]")
    assertEquals("01..01", r.episodeRange.toString())
    assertEquals("CHS, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EMBEDDED, r.subtitleKind)
    }
  }

  @Test
  public fun `636638_LoliHouse_Dr_STONE_S3___01_WebRip_1080p_HEVC_10bit_AAC`() {
    kotlin.run {
    val r =
        parse("[豌豆字幕组&风之圣殿字幕组&LoliHouse] 新石纪 第三季 / 石纪元 第三季 / Dr.STONE S3 - 01 [WebRip 1080p HEVC-10bit AAC][简繁外挂字幕]")
    assertEquals("01..01", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public fun `605298_NC_Raws_Dr_Stone_Ryuusui___02_B_Global_1920x1080_HEVC_AAC_MKV`() {
    kotlin.run {
    val r =
        parse("[NC-Raws] Ｄｒ．ＳＴＯＮＥ 石纪元 特别篇(龙水) / Dr. Stone: Ryuusui - 02 (B-Global 1920x1080 HEVC AAC MKV)")
    assertEquals("02..02", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `605294_NC_Raws_Dr_Stone_Ryuusui___01_B_Global_1920x1080_HEVC_AAC_MKV`() {
    kotlin.run {
    val r =
        parse("[NC-Raws] Ｄｒ．ＳＴＯＮＥ 石纪元 特别篇(龙水) / Dr. Stone: Ryuusui - 01 (B-Global 1920x1080 HEVC AAC MKV)")
    assertEquals("01..01", r.episodeRange.toString())
    assertEquals("", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public
      fun `600372_jibaketa_ViuTV_Dr_STONE_STONE_WARS___11_END_BD_1920x1080_x264_AACx2_PGS_SRT_ViuTV_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&壓制][ViuTV粵語]Dr.STONE 新石紀 / 石紀元 第二季 石之戰爭 / STONE WARS - 11 END [粵日雙語+內封繁體中文雙字幕] (BD 1920x1080 x264 AACx2 PGS+SRT ViuTV CHT)")
    assertEquals("11..11", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `599745_jibaketa_ViuTV_Dr_STONE_STONE_WARS___10_BD_1920x1080_x264_AACx2_PGS_SRT_ViuTV_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&壓制][ViuTV粵語]Dr.STONE 新石紀 / 石紀元 第二季 石之戰爭 / STONE WARS - 10 [粵日雙語+內封繁體中文雙字幕] (BD 1920x1080 x264 AACx2 PGS+SRT ViuTV CHT)")
    assertEquals("10..10", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `599318_jibaketa_ViuTV_Dr_STONE_BD_1920x1080_x264_25fps_AAC_PGS_SRT_ViuTV_CAN_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&壓制][ViuTV粵語]Dr.STONE新石紀 / 石紀元 [粵語+內封繁體中文雙字幕] (BD 1920x1080 x264 25fps AAC PGS+SRT ViuTV CAN CHT)")
    assertEquals("S?", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `599179_jibaketa_ViuTV_Dr_STONE_STONE_WARS___09_BD_1920x1080_x264_AACx2_PGS_SRT_ViuTV_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&壓制][ViuTV粵語]Dr.STONE 新石紀 / 石紀元 第二季 石之戰爭 / STONE WARS - 09 [粵日雙語+內封繁體中文雙字幕] (BD 1920x1080 x264 AACx2 PGS+SRT ViuTV CHT)")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `598479_jibaketa_ViuTV_Dr_STONE_STONE_WARS___08_BD_1920x1080_x264_AACx2_PGS_SRT_ViuTV_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&壓制][ViuTV粵語]Dr.STONE 新石紀 / 石紀元 第二季 石之戰爭 / STONE WARS - 08 [粵日雙語+內封繁體中文雙字幕] (BD 1920x1080 x264 AACx2 PGS+SRT ViuTV CHT)")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `597847_jibaketa_ViuTV_Dr_STONE_STONE_WARS___07_BD_1920x1080_x264_AACx2_PGS_SRT_ViuTV_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&壓制][ViuTV粵語]Dr.STONE 新石紀 / 石紀元 第二季 石之戰爭 / STONE WARS - 07 [粵日雙語+內封繁體中文雙字幕] (BD 1920x1080 x264 AACx2 PGS+SRT ViuTV CHT)")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `597170_jibaketa_ViuTV_Dr_STONE_STONE_WARS___06_BD_1920x1080_x264_AACx2_PGS_SRT_ViuTV_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&壓制][ViuTV粵語]Dr.STONE 新石紀 / 石紀元 第二季 石之戰爭 / STONE WARS - 06 [粵日雙語+內封繁體中文雙字幕] (BD 1920x1080 x264 AACx2 PGS+SRT ViuTV CHT)")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `596508_jibaketa_ViuTV_Dr_STONE_STONE_WARS___05_BD_1920x1080_x264_AACx2_PGS_SRT_ViuTV_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&壓制][ViuTV粵語]Dr.STONE 新石紀 / 石紀元 第二季 石之戰爭 / STONE WARS - 05 [粵日雙語+內封繁體中文雙字幕] (BD 1920x1080 x264 AACx2 PGS+SRT ViuTV CHT)")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `596056_jibaketa_ViuTV_Dr_STONE_STONE_WARS___04_BD_1920x1080_x264_AACx2_PGS_SRT_ViuTV_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&壓制][ViuTV粵語]Dr.STONE 新石紀 / 石紀元 第二季 石之戰爭 / STONE WARS - 04 [粵日雙語+內封繁體中文雙字幕] (BD 1920x1080 x264 AACx2 PGS+SRT ViuTV CHT)")
    assertEquals("04..04", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `595481_jibaketa_ViuTV_Dr_STONE_STONE_WARS___03_BD_1920x1080_x264_AACx2_PGS_SRT_ViuTV_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&壓制][ViuTV粵語]Dr.STONE 新石紀 / 石紀元 第二季 石之戰爭 / STONE WARS - 03 [粵日雙語+內封繁體中文雙字幕] (BD 1920x1080 x264 AACx2 PGS+SRT ViuTV CHT)")
    assertEquals("03..03", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `595480_jibaketa_ViuTV_Dr_STONE_STONE_WARS___02_BD_1920x1080_x264_AACx2_PGS_SRT_ViuTV_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&壓制][ViuTV粵語]Dr.STONE 新石紀 / 石紀元 第二季 石之戰爭 / STONE WARS - 02 [粵日雙語+內封繁體中文雙字幕] (BD 1920x1080 x264 AACx2 PGS+SRT ViuTV CHT)")
    assertEquals("02..02", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `595218_DBD_Raws_Dr_STONE_01_24TV_1080P_BDRip_HEVC_10bit_FLAC_MKV`() {
    kotlin.run {
    val r =
        parse("[DBD-Raws][石纪元/Dr.STONE][01-24TV全集+特典映像][1080P][BDRip][HEVC-10bit][简繁外挂][FLAC][MKV]")
    assertEquals("01..24", r.episodeRange.toString())
    assertEquals("CHS, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.EXTERNAL_DISCOVER, r.subtitleKind)
    }
  }

  @Test
  public
      fun `594763_jibaketa_ViuTV_Dr_STONE_STONE_WARS___01_BD_1920x1080_x264_AACx2_PGS_SRT_ViuTV_CHT_V2`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&壓制][ViuTV粵語]Dr.STONE 新石紀 / 石紀元 第二季 石之戰爭 / STONE WARS - 01 [粵日雙語+內封繁體中文雙字幕] (BD 1920x1080 x264 AACx2 PGS+SRT ViuTV CHT V2)")
    assertEquals("01..01", r.episodeRange.toString())
    assertEquals("CHC, CHT, JPN", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `594003_jibaketa_ViuTV_Dr_STONE_Dr_STONE_Dr_STONE___24_END_WEB_1920x1080_AVC_25fps_AAC_PGS_SRT_ViuTV_CAN_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][ViuTV粵語] Dr.STONE 新石紀 / Dr.STONE 石紀元 / Dr.STONE - 24 END [粵語+內封繁體中文雙字幕] (WEB 1920x1080 AVC 25fps AAC PGS+SRT ViuTV CAN CHT)")
    assertEquals("24..24", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `593296_jibaketa_ViuTV_Dr_STONE_Dr_STONE_Dr_STONE___23_WEB_1920x1080_AVC_25fps_AAC_PGS_SRT_ViuTV_CAN_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][ViuTV粵語] Dr.STONE 新石紀 / Dr.STONE 石紀元 / Dr.STONE - 23 [粵語+內封繁體中文雙字幕] (WEB 1920x1080 AVC 25fps AAC PGS+SRT ViuTV CAN CHT)")
    assertEquals("23..23", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `592849_jibaketa_ViuTV_Dr_STONE_Dr_STONE_Dr_STONE___22_WEB_1920x1080_AVC_25fps_AAC_PGS_SRT_ViuTV_CAN_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][ViuTV粵語] Dr.STONE 新石紀 / Dr.STONE 石紀元 / Dr.STONE - 22 [粵語+內封繁體中文雙字幕] (WEB 1920x1080 AVC 25fps AAC PGS+SRT ViuTV CAN CHT)")
    assertEquals("22..22", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `592288_jibaketa_ViuTV_Dr_STONE_Dr_STONE_Dr_STONE___21_WEB_1920x1080_AVC_25fps_AAC_PGS_SRT_ViuTV_CAN_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][ViuTV粵語] Dr.STONE 新石紀 / Dr.STONE 石紀元 / Dr.STONE - 21 [粵語+內封繁體中文雙字幕] (WEB 1920x1080 AVC 25fps AAC PGS+SRT ViuTV CAN CHT)")
    assertEquals("21..21", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `591726_jibaketa_ViuTV_Dr_STONE_Dr_STONE_Dr_STONE___20_WEB_1920x1080_AVC_25fps_AAC_PGS_SRT_ViuTV_CAN_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][ViuTV粵語] Dr.STONE 新石紀 / Dr.STONE 石紀元 / Dr.STONE - 20 [粵語+內封繁體中文雙字幕] (WEB 1920x1080 AVC 25fps AAC PGS+SRT ViuTV CAN CHT)")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `590971_jibaketa_ViuTV_Dr_STONE_Dr_STONE_Dr_STONE___19_WEB_1920x1080_AVC_25fps_AAC_PGS_SRT_ViuTV_CAN_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][ViuTV粵語] Dr.STONE 新石紀 / Dr.STONE 石紀元 / Dr.STONE - 19 [粵語+內封繁體中文雙字幕] (WEB 1920x1080 AVC 25fps AAC PGS+SRT ViuTV CAN CHT)")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `590440_jibaketa_ViuTV_Dr_STONE_Dr_STONE_Dr_STONE___18_WEB_1920x1080_AVC_25fps_AAC_PGS_SRT_ViuTV_CAN_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][ViuTV粵語] Dr.STONE 新石紀 / Dr.STONE 石紀元 / Dr.STONE - 18 [粵語+內封繁體中文雙字幕] (WEB 1920x1080 AVC 25fps AAC PGS+SRT ViuTV CAN CHT)")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `589820_jibaketa_ViuTV_Dr_STONE_Dr_STONE_Dr_STONE___17_WEB_1920x1080_AVC_25fps_AAC_PGS_SRT_ViuTV_CAN_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][ViuTV粵語] Dr.STONE 新石紀 / Dr.STONE 石紀元 / Dr.STONE - 17 [粵語+內封繁體中文雙字幕] (WEB 1920x1080 AVC 25fps AAC PGS+SRT ViuTV CAN CHT)")
    assertEquals("17..17", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `589467_jibaketa_ViuTV_Dr_STONE_Dr_STONE_Dr_STONE___16_WEB_1920x1080_AVC_25fps_AAC_PGS_SRT_ViuTV_CAN_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][ViuTV粵語]Dr.STONE新石紀 / Dr.STONE 石紀元 / Dr.STONE - 16 [粵語+內封繁體中文雙字幕] (WEB 1920x1080 AVC 25fps AAC PGS+SRT ViuTV CAN CHT)")
    assertEquals("16..16", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `588974_jibaketa_ViuTV_Dr_STONE_Dr_STONE_Dr_STONE___15_WEB_1920x1080_AVC_25fps_AAC_PGS_SRT_ViuTV_CAN_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][ViuTV粵語]Dr.STONE新石紀 / Dr.STONE 石紀元 / Dr.STONE - 15 [粵語+內封繁體中文雙字幕] (WEB 1920x1080 AVC 25fps AAC PGS+SRT ViuTV CAN CHT)")
    assertEquals("15..15", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `588544_jibaketa_ViuTV_Dr_STONE_Dr_STONE_Dr_STONE___14_WEB_1920x1080_AVC_25fps_AAC_PGS_SRT_ViuTV_CAN_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][ViuTV粵語]Dr.STONE新石紀 / Dr.STONE 石紀元 / Dr.STONE - 14 [粵語+內封繁體中文雙字幕] (WEB 1920x1080 AVC 25fps AAC PGS+SRT ViuTV CAN CHT)")
    assertEquals("14..14", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `587869_jibaketa_ViuTV_Dr_STONE_Dr_STONE_Dr_STONE___13_WEB_1920x1080_AVC_25fps_AAC_PGS_SRT_ViuTV_CAN_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][ViuTV粵語]Dr.STONE新石紀 / Dr.STONE 石紀元 / Dr.STONE - 13 [粵語+內封繁體中文雙字幕] (WEB 1920x1080 AVC 25fps AAC PGS+SRT ViuTV CAN CHT)")
    assertEquals("13..13", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `587226_jibaketa_ViuTV_Dr_STONE_Dr_STONE_Dr_STONE___12_WEB_1920x1080_AVC_25fps_AAC_PGS_SRT_ViuTV_CAN_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][ViuTV粵語]Dr.STONE新石紀 / Dr.STONE 石紀元 / Dr.STONE - 12 [粵語+內封繁體中文雙字幕] (WEB 1920x1080 AVC 25fps AAC PGS+SRT ViuTV CAN CHT)")
    assertEquals("12..12", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `586898_jibaketa_ViuTV_Dr_STONE_Dr_STONE_Dr_STONE___11_WEB_1920x1080_AVC_25fps_AAC_PGS_SRT_ViuTV_CAN_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][ViuTV粵語]Dr.STONE新石紀 / Dr.STONE 石紀元 / Dr.STONE - 11 [粵語+內封繁體中文雙字幕] (WEB 1920x1080 AVC 25fps AAC PGS+SRT ViuTV CAN CHT)")
    assertEquals("11..11", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `586183_jibaketa_ViuTV_Dr_STONE_Dr_STONE_Dr_STONE___10_WEB_1920x1080_AVC_25fps_AAC_PGS_SRT_ViuTV_CAN_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][ViuTV粵語]Dr.STONE新石紀 / Dr.STONE 石紀元 / Dr.STONE - 10 [粵語+內封繁體中文雙字幕] (WEB 1920x1080 AVC 25fps AAC PGS+SRT ViuTV CAN CHT)")
    assertEquals("10..10", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `585881_jibaketa_ViuTV_Dr_STONE_Dr_STONE_Dr_STONE___09_WEB_1920x1080_AVC_AAC_PGS_SRT_ViuTV_CAN_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][ViuTV粵語]Dr.STONE新石紀 / Dr.STONE 石紀元 / Dr.STONE - 09 [粵語+內封繁體中文雙字幕] (WEB 1920x1080 AVC AAC PGS+SRT ViuTV CAN CHT)")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `584984_jibaketa_ViuTV_Dr_STONE_Dr_STONE_Dr_STONE___08_WEB_1920x1080_AVC_AAC_PGS_SRT_ViuTV_CAN_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][ViuTV粵語]Dr.STONE新石紀 / Dr.STONE 石紀元 / Dr.STONE - 08 [粵語+內封繁體中文雙字幕] (WEB 1920x1080 AVC AAC PGS+SRT ViuTV CAN CHT)")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `584338_jibaketa_ViuTV_Dr_STONE_Dr_STONE_Dr_STONE___07_WEB_1920x1080_AVC_AAC_PGS_SRT_ViuTV_CAN_CHT_V2`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][ViuTV粵語]Dr.STONE新石紀 / Dr.STONE 石紀元 / Dr.STONE - 07 [粵語+內封繁體中文雙字幕] (WEB 1920x1080 AVC AAC PGS+SRT ViuTV CAN CHT V2)")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `583258_jibaketa_ViuTV_Dr_STONE_Dr_STONE_Dr_STONE___06_WEB_1920x1080_AVC_AAC_PGS_SRT_ViuTV_CAN_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][ViuTV粵語]Dr.STONE新石紀 / Dr.STONE 石紀元 / Dr.STONE - 06 [粵語+內封繁體中文雙字幕] (WEB 1920x1080 AVC AAC PGS+SRT ViuTV CAN CHT)")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `581717_jibaketa_ViuTV_Dr_STONE_Dr_STONE_Dr_STONE___05_WEB_1920x1080_AVC_AAC_PGS_SRT_ViuTV_CAN_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][ViuTV粵語]Dr.STONE新石紀 / Dr.STONE 石紀元 / Dr.STONE - 05 [粵語+內封繁體中文雙字幕] (WEB 1920x1080 AVC AAC PGS+SRT ViuTV CAN CHT)")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `581238_jibaketa_ViuTV_Dr_STONE_Dr_STONE_Dr_STONE___04_WEB_1920x1080_AVC_AAC_PGS_SRT_ViuTV_CAN_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][ViuTV粵語]Dr.STONE新石紀 / Dr.STONE 石紀元 / Dr.STONE - 04 [粵語+內封繁體中文雙字幕] (WEB 1920x1080 AVC AAC PGS+SRT ViuTV CAN CHT)")
    assertEquals("04..04", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `580464_jibaketa_ViuTV_Dr_STONE_Dr_STONE_Dr_STONE___03_WEB_1920x1080_AVC_AAC_PGS_SRT_ViuTV_CAN_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][ViuTV粵語]Dr.STONE新石紀 / Dr.STONE 石紀元 / Dr.STONE - 03 [粵語+內封繁體中文雙字幕] (WEB 1920x1080 AVC AAC PGS+SRT ViuTV CAN CHT)")
    assertEquals("03..03", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `579687_jibaketa_ViuTV_Dr_STONE_Dr_STONE_Dr_STONE___02_WEB_1920x1080_AVC_AAC_PGS_SRT_ViuTV_CAN_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][ViuTV粵語]Dr.STONE新石紀 / Dr.STONE 石紀元 / Dr.STONE - 02 [粵語+內封繁體中文雙字幕] (WEB 1920x1080 AVC AAC PGS+SRT ViuTV CAN CHT)")
    assertEquals("02..02", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public
      fun `579103_jibaketa_ViuTV_Dr_STONE_Dr_STONE_Dr_STONE___01_WEB_1920x1080_AVC_AAC_PGS_SRT_ViuTV_CAN_CHT`() {
    kotlin.run {
    val r =
        parse("[jibaketa合成&音頻壓制][ViuTV粵語]Dr.STONE新石紀 / Dr.STONE 石紀元 / Dr.STONE - 01 [粵語+內封繁體中文雙字幕] (WEB 1920x1080 AVC AAC PGS+SRT ViuTV CAN CHT)")
    assertEquals("01..01", r.episodeRange.toString())
    assertEquals("CHC, CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(SubtitleKind.CLOSED, r.subtitleKind)
    }
  }

  @Test
  public fun `565119_10_Stone_Wars_Dr_Stone_S2_Stone_Wars_01_11_BIG5_1080P_HEVC_MP4`() {
    kotlin.run {
    val r =
        parse("【極影字幕社】 ★10月新番 石紀元第二季 Stone Wars Dr.Stone S2 Stone Wars 第01-11話 BIG5 1080P HEVC MP4（字幕社招人內詳）")
    assertEquals("01..11", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `565118_10_Stone_Wars_Dr_Stone_S2_Stone_Wars_01_11_BIG5_1080P_AVC_MP4`() {
    kotlin.run {
    val r =
        parse("【極影字幕社】 ★10月新番 石紀元第二季 Stone Wars Dr.Stone S2 Stone Wars 第01-11話 BIG5 1080P AVC MP4（字幕社招人內詳）")
    assertEquals("01..11", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `565117_10_Stone_Wars_Dr_Stone_S2_Stone_Wars_01_11_BIG5_720P_AVC_MP4`() {
    kotlin.run {
    val r =
        parse("【極影字幕社】 ★10月新番 石紀元第二季 Stone Wars Dr.Stone S2 Stone Wars 第01-11話 BIG5 720P AVC MP4（字幕社招人內詳）")
    assertEquals("01..11", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("720P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `565116_01_Stone_Wars_Dr_Stone_S2_Stone_Wars_01_11_GB_1080P_HEVC_MP4`() {
    kotlin.run {
    val r =
        parse("【极影字幕社】 ★01月新番 石纪元第二季 Stone Wars Dr.Stone S2 Stone Wars 第01-11话 GB 1080P HEVC MP4（字幕社招人内详）")
    assertEquals("01..11", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `565115_01_Stone_Wars_Dr_Stone_S2_Stone_Wars_01_11_GB_1080P_AVC_MP4`() {
    kotlin.run {
    val r =
        parse("【极影字幕社】 ★01月新番 石纪元第二季 Stone Wars Dr.Stone S2 Stone Wars 第01-11话 GB 1080P AVC MP4（字幕社招人内详）")
    assertEquals("01..11", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `565114_01_Stone_Wars_Dr_Stone_S2_Stone_Wars_01_11_GB_720P_AVC_MP4`() {
    kotlin.run {
    val r =
        parse("【极影字幕社】 ★01月新番 石纪元第二季 Stone Wars Dr.Stone S2 Stone Wars 第01-11话 GB 720P AVC MP4（字幕社招人内详）")
    assertEquals("01..11", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("720P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `564972_c_c_1_01_11_BIG5_1080P_MP4`() {
    kotlin.run {
    val r = parse("[c.c動漫][1月新番][新石紀 / 石紀元 第二季][01-11][合集][BIG5][1080P][MP4]")
    assertEquals("01..11", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `564548_10_Stone_Wars_Dr_Stone_S2_Stone_Wars_11_BIG5_1080P_HEVC_MP4`() {
    kotlin.run {
    val r =
        parse("【極影字幕社】 ★10月新番 石紀元第二季 Stone Wars Dr.Stone S2 Stone Wars 第11話 BIG5 1080P HEVC MP4（字幕社招人內詳）")
    assertEquals("11..11", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `564547_01_Stone_Wars_Dr_Stone_S2_Stone_Wars_11_GB_1080P_HEVC_MP4`() {
    kotlin.run {
    val r =
        parse("【极影字幕社】 ★01月新番 石纪元第二季 Stone Wars Dr.Stone S2 Stone Wars 第11话 GB 1080P HEVC MP4（字幕社招人内详）")
    assertEquals("11..11", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `563922_10_Stone_Wars_Dr_Stone_S2_Stone_Wars_10_BIG5_1080P_HEVC_MP4`() {
    kotlin.run {
    val r =
        parse("【極影字幕社】 ★10月新番 石紀元第二季 Stone Wars Dr.Stone S2 Stone Wars 第10話 BIG5 1080P HEVC MP4（字幕社招人內詳）")
    assertEquals("10..10", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `563921_01_Stone_Wars_Dr_Stone_S2_Stone_Wars_10_GB_1080P_HEVC_MP4`() {
    kotlin.run {
    val r =
        parse("【极影字幕社】 ★01月新番 石纪元第二季 Stone Wars Dr.Stone S2 Stone Wars 第10话 GB 1080P HEVC MP4（字幕社招人内详）")
    assertEquals("10..10", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `563737_c_c_1_11_GB_HEVC_1080P_MP4_END`() {
    kotlin.run {
    val r = parse("[c.c动漫][1月新番][新石纪 / 石纪元 第二季][11][GB][HEVC][1080P][MP4][END]")
    assertEquals("11..11", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `563164_10_Stone_Wars_Dr_Stone_S2_Stone_Wars_09_BIG5_1080P_HEVC_MP4`() {
    kotlin.run {
    val r =
        parse("【極影字幕社】 ★10月新番 石紀元第二季 Stone Wars Dr.Stone S2 Stone Wars 第09話 BIG5 1080P HEVC MP4（字幕社招人內詳）")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `563163_01_Stone_Wars_Dr_Stone_S2_Stone_Wars_09_GB_1080P_HEVC_MP4`() {
    kotlin.run {
    val r =
        parse("【极影字幕社】 ★01月新番 石纪元第二季 Stone Wars Dr.Stone S2 Stone Wars 第09话 GB 1080P HEVC MP4（字幕社招人内详）")
    assertEquals("09..09", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `563035_c_c_1_10_GB_HEVC_1080P_MP4`() {
    kotlin.run {
    val r = parse("[c.c动漫][1月新番][新石纪 / 石纪元 第二季][10][GB][HEVC][1080P][MP4]")
    assertEquals("10..10", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `562887_10_Stone_Wars_Dr_Stone_S2_Stone_Wars_08_BIG5_1080P_HEVC_MP4`() {
    kotlin.run {
    val r =
        parse("【極影字幕社】 ★10月新番 石紀元第二季 Stone Wars Dr.Stone S2 Stone Wars 第08話 BIG5 1080P HEVC MP4（字幕社招人內詳）")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `562886_01_Stone_Wars_Dr_Stone_S2_Stone_Wars_08_GB_1080P_HEVC_MP4`() {
    kotlin.run {
    val r =
        parse("【极影字幕社】 ★01月新番 石纪元第二季 Stone Wars Dr.Stone S2 Stone Wars 第08话 GB 1080P HEVC MP4（字幕社招人内详）")
    assertEquals("08..08", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `562753_c_c_1_08_09_GB_HEVC_1080P_MP4`() {
    kotlin.run {
    val r = parse("[c.c动漫][1月新番][新石纪 / 石纪元 第二季][08-09][GB][HEVC][1080P][MP4]")
    assertEquals("08..09", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `561998_10_Stone_Wars_Dr_Stone_S2_Stone_Wars_07_BIG5_1080P_HEVC_MP4`() {
    kotlin.run {
    val r =
        parse("【極影字幕社】 ★10月新番 石紀元第二季 Stone Wars Dr.Stone S2 Stone Wars 第07話 BIG5 1080P HEVC MP4（字幕社招人內詳）")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `561997_01_Stone_Wars_Dr_Stone_S2_Stone_Wars_07_GB_1080P_HEVC_MP4`() {
    kotlin.run {
    val r =
        parse("【极影字幕社】 ★01月新番 石纪元第二季 Stone Wars Dr.Stone S2 Stone Wars 第07话 GB 1080P HEVC MP4（字幕社招人内详）")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `561616_10_Stone_Wars_Dr_Stone_S2_Stone_Wars_06_BIG5_1080P_HEVC_MP4`() {
    kotlin.run {
    val r =
        parse("【極影字幕社】 ★10月新番 石紀元第二季 Stone Wars Dr.Stone S2 Stone Wars 第06話 BIG5 1080P HEVC MP4（字幕社招人內詳）")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `561615_01_Stone_Wars_Dr_Stone_S2_Stone_Wars_06_GB_1080P_HEVC_MP4`() {
    kotlin.run {
    val r =
        parse("【极影字幕社】 ★01月新番 石纪元第二季 Stone Wars Dr.Stone S2 Stone Wars 第06话 GB 1080P HEVC MP4（字幕社招人内详）")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `561083_c_c_1_07_GB_HEVC_1080P_MP4`() {
    kotlin.run {
    val r = parse("[c.c动漫][1月新番][新石纪 / 石纪元 第二季][07][GB][HEVC][1080P][MP4][暫停更新公告]")
    assertEquals("07..07", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `560294_c_c_1_06_GB_HEVC_1080P_MP4`() {
    kotlin.run {
    val r = parse("[c.c动漫][1月新番][新石纪 / 石纪元 第二季][06][GB][HEVC][1080P][MP4]")
    assertEquals("06..06", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `560085_10_Stone_Wars_Dr_Stone_S2_Stone_Wars_05_BIG5_1080P_HEVC_MP4`() {
    kotlin.run {
    val r =
        parse("【極影字幕社】 ★10月新番 石紀元第二季 Stone Wars Dr.Stone S2 Stone Wars 第05話 BIG5 1080P HEVC MP4（字幕社招人內詳）")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `560084_01_Stone_Wars_Dr_Stone_S2_Stone_Wars_05_GB_1080P_HEVC_MP4`() {
    kotlin.run {
    val r =
        parse("【极影字幕社】 ★01月新番 石纪元第二季 Stone Wars Dr.Stone S2 Stone Wars 第05话 GB 1080P HEVC MP4（字幕社招人内详）")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `559707_c_c_1_05_GB_HEVC_1080P_MP4`() {
    kotlin.run {
    val r = parse("[c.c动漫][1月新番][新石纪 / 石纪元 第二季][05][GB][HEVC][1080P][MP4]")
    assertEquals("05..05", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `559674_10_Stone_Wars_Dr_Stone_S2_Stone_Wars_04_BIG5_1080P_HEVC_MP4`() {
    kotlin.run {
    val r =
        parse("【極影字幕社】 ★10月新番 石紀元第二季 Stone Wars Dr.Stone S2 Stone Wars 第04話 BIG5 1080P HEVC MP4（字幕社招人內詳）")
    assertEquals("04..04", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `559673_01_Stone_Wars_Dr_Stone_S2_Stone_Wars_04_GB_1080P_HEVC_MP4`() {
    kotlin.run {
    val r =
        parse("【极影字幕社】 ★01月新番 石纪元第二季 Stone Wars Dr.Stone S2 Stone Wars 第04话 GB 1080P HEVC MP4（字幕社招人内详）")
    assertEquals("04..04", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `559009_c_c_1_04_GB_HEVC_1080P_MP4`() {
    kotlin.run {
    val r = parse("[c.c动漫][1月新番][新石纪 / 石纪元 第二季][04][GB][HEVC][1080P][MP4]")
    assertEquals("04..04", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `558951_10_Stone_Wars_Dr_Stone_S2_Stone_Wars_03_BIG5_1080P_HEVC_MP4`() {
    kotlin.run {
    val r =
        parse("【極影字幕社】 ★10月新番 石紀元第二季 Stone Wars Dr.Stone S2 Stone Wars 第03話 BIG5 1080P HEVC MP4（字幕社招人內詳）")
    assertEquals("03..03", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `558950_01_Stone_Wars_Dr_Stone_S2_Stone_Wars_03_GB_1080P_HEVC_MP4`() {
    kotlin.run {
    val r =
        parse("【极影字幕社】 ★01月新番 石纪元第二季 Stone Wars Dr.Stone S2 Stone Wars 第03话 GB 1080P HEVC MP4（字幕社招人内详）")
    assertEquals("03..03", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `558358_c_c_1_03_GB_HEVC_1080P_MP4`() {
    kotlin.run {
    val r = parse("[c.c动漫][1月新番][新石纪 / 石纪元 第二季][03][GB][HEVC][1080P][MP4]")
    assertEquals("03..03", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `558030_10_Stone_Wars_Dr_Stone_S2_Stone_Wars_02_BIG5_1080P_HEVC_MP4`() {
    kotlin.run {
    val r =
        parse("【極影字幕社】 ★10月新番 石紀元第二季 Stone Wars Dr.Stone S2 Stone Wars 第02話 BIG5 1080P HEVC MP4（字幕社招人內詳）")
    assertEquals("02..02", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `558029_01_Stone_Wars_Dr_Stone_S2_Stone_Wars_02_GB_1080P_HEVC_MP4`() {
    kotlin.run {
    val r =
        parse("【极影字幕社】 ★01月新番 石纪元第二季 Stone Wars Dr.Stone S2 Stone Wars 第02话 GB 1080P HEVC MP4（字幕社招人内详）")
    assertEquals("02..02", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `557644_c_c_1_02_GB_HEVC_1080P_MP4`() {
    kotlin.run {
    val r = parse("[c.c动漫][1月新番][新石纪 / 石纪元 第二季][02][GB][HEVC][1080P][MP4]")
    assertEquals("02..02", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `557507_10_Stone_Wars_Dr_Stone_S2_Stone_Wars_01_BIG5_1080P_HEVC_MP4`() {
    kotlin.run {
    val r =
        parse("【極影字幕社】 ★10月新番 石紀元第二季 Stone Wars Dr.Stone S2 Stone Wars 第01話 BIG5 1080P HEVC MP4（字幕社招人內詳）")
    assertEquals("01..01", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `557505_01_Stone_Wars_Dr_Stone_S2_Stone_Wars_01_GB_1080P_MP4`() {
    kotlin.run {
    val r =
        parse("【极影字幕社】 ★01月新番 石纪元第二季 Stone Wars Dr.Stone S2 Stone Wars 第01话 GB 1080P MP4（字幕社招人内详）")
    assertEquals("01..01", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `557001_c_c_1_01_GB_HEVC_1080P_MP4`() {
    kotlin.run {
    val r = parse("[c.c动漫][1月新番][新石纪 / 石纪元 第二季][01][GB][HEVC][1080P][MP4]")
    assertEquals("01..01", r.episodeRange.toString())
    assertEquals("CHS", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `531015_c_c_7_Dr_STONE_24_BIG5_1080P_MP4_END`() {
    kotlin.run {
    val r = parse("[c.c動漫][7月新番][新石紀 / 石紀元][Dr.STONE][24][BIG5][1080P][MP4][END][網盤]")
    assertEquals("24..24", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `530498_c_c_7_Dr_STONE_23_BIG5_1080P_MP4`() {
    kotlin.run {
    val r = parse("[c.c動漫][7月新番][新石紀 / 石紀元][Dr.STONE][23][BIG5][1080P][MP4][網盤]")
    assertEquals("23..23", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `530004_c_c_7_Dr_STONE_22_BIG5_1080P_MP4`() {
    kotlin.run {
    val r = parse("[c.c動漫][7月新番][新石紀 / 石紀元][Dr.STONE][22][BIG5][1080P][MP4][網盤]")
    assertEquals("22..22", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `529474_c_c_7_Dr_STONE_21_BIG5_1080P_MP4`() {
    kotlin.run {
    val r = parse("[c.c動漫][7月新番][新石紀 / 石紀元][Dr.STONE][21][BIG5][1080P][MP4][網盤]")
    assertEquals("21..21", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `528954_c_c_7_Dr_STONE_20_BIG5_1080P_MP4`() {
    kotlin.run {
    val r = parse("[c.c動漫][7月新番][新石紀 / 石紀元][Dr.STONE][20][BIG5][1080P][MP4][網盤]")
    assertEquals("20..20", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `528484_c_c_7_Dr_STONE_19_BIG5_1080P_MP4`() {
    kotlin.run {
    val r = parse("[c.c動漫][7月新番][新石紀 / 石紀元][Dr.STONE][19][BIG5][1080P][MP4][網盤]")
    assertEquals("19..19", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `527990_c_c_7_Dr_STONE_18_BIG5_1080P_MP4`() {
    kotlin.run {
    val r = parse("[c.c動漫][7月新番][新石紀 / 石紀元][Dr.STONE][18][BIG5][1080P][MP4][網盤]")
    assertEquals("18..18", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `527515_c_c_7_Dr_STONE_17_BIG5_1080P_MP4`() {
    kotlin.run {
    val r = parse("[c.c動漫][7月新番][新石紀 / 石紀元][Dr.STONE][17][BIG5][1080P][MP4][網盤]")
    assertEquals("17..17", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }

  @Test
  public fun `526972_c_c_7_Dr_STONE_16_BIG5_1080P_MP4`() {
    kotlin.run {
    val r = parse("[c.c動漫][7月新番][新石紀 / 石紀元][Dr.STONE][16][BIG5][1080P][MP4][網盤]")
    assertEquals("16..16", r.episodeRange.toString())
    assertEquals("CHT", r.subtitleLanguages.sortedBy { it.id }.joinToString { it.id })
    assertEquals("1080P", r.resolution.toString())
    assertEquals(null, r.subtitleKind)
    }
  }
}

// @formatter:on
