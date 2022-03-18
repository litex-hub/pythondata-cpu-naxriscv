//LOAD / STORE
plugins += new LsuPlugin(
  lqSize = 16,
  sqSize = 16,
  loadToCacheBypass = true,
  lqToCachePipelined = true,
  hitPedictionEntries = 1024,
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
    priority = 1
  ),
  loadTranslationParameter  = MmuPortParameter(
    readAt = 0,
    hitsAt = 0,
    ctrlAt = 1,
    rspAt  = 1
  ),
  storeTranslationParameter = MmuPortParameter(
    readAt = 1,
    hitsAt = 1,
    ctrlAt = 1,
    rspAt  = 1
  )
)
plugins += new DataCachePlugin(
  memDataWidth = 64,
  cacheSize    = 4096*4,
  wayCount     = 4,
  refillCount = 2,
  writebackCount = 2,
  tagsReadAsync = true,
  reducedBankWidth = false,
  loadRefillCheckEarly = false
)

