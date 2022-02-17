//EXECUTION UNITES
plugins += new ExecutionUnitBase("EU0")
plugins += new SrcPlugin("EU0", earlySrc = true)
plugins += new IntAluPlugin("EU0", aluStage = 0)
plugins += new ShiftPlugin("EU0" , aluStage = 0)

plugins += new ExecutionUnitBase("EU1", writebackCountMax = 1)
plugins += new SrcPlugin("EU1", earlySrc = true)
plugins += new MulPlugin("EU1", writebackAt = 2, staticLatency = false)
plugins += new DivPlugin("EU1", writebackAt = 2)
plugins += new LoadPlugin("EU1")
plugins += new StorePlugin("EU1")
plugins += new BranchPlugin("EU1", writebackAt = 2, staticLatency = false)
plugins += new EnvCallPlugin("EU1")(rescheduleAt = 2)
plugins += new CsrAccessPlugin("EU1")(
  decodeAt = 0,
  readAt = 1,
  writeAt = 2,
  writebackAt = 2,
  staticLatency = false
)




