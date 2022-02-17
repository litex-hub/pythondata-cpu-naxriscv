//BRANCH PREDICTION
plugins += new BranchContextPlugin(
  branchCount = 16
)
plugins += new HistoryPlugin(
  historyFetchBypass = true
)
plugins += new DecoderPredictionPlugin(
  flushOnBranch = false //TODO remove me (DEBUG)
)
plugins += new BtbPlugin(
//      entries = 8192*8,
  entries = 512,
  readAt = 0,
  hitAt = 1,
  jumpAt = 1
)
plugins += new GSharePlugin(
//      entries = 1 << 24,
  memBytes = 4 KiB,
  historyWidth = 24,
  readAt = 0
)
