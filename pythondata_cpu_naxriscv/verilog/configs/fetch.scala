//FETCH
plugins += new FetchPlugin()
plugins += new PcPlugin(resetVector)
plugins += new FetchCachePlugin(
  cacheSize = 4096*4,
  wayCount = 4,
  injectionAt = 2,
  fetchDataWidth = 64,
  memDataWidth = 64,
  reducedBankWidth = false,
  hitsWithTranslationWays = true,
  translationStorageParameter = MmuStorageParameter(
    levels   = List(
      MmuStorageLevel(
        id    = 0,
        ways  = 4,
        depth = 32
      ),
      MmuStorageLevel(
        id    = 1,
        ways  = 2,
        depth = 32
      )
    ),
    priority = 0
  ),
  translationPortParameter  = MmuPortParameter(
    readAt = 1,
    hitsAt = 1,
    ctrlAt = 1,
    rspAt  = 1
  )
)
plugins += new AlignerPlugin(
  decodeCount = 2,
  inputAt = 2
)
