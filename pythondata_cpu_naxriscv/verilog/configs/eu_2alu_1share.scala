//EXECUTION UNITES
plugins += new ExecutionUnitBase("EU0")
plugins += new SrcPlugin("EU0", earlySrc = true)
plugins += new IntAluPlugin("EU0", aluStage = 0)
plugins += new ShiftPlugin("EU0" , aluStage = 0)
plugins += new BranchPlugin("EU0")

plugins += new ExecutionUnitBase("EU1")
plugins += new SrcPlugin("EU1")
plugins += new IntAluPlugin("EU1")
plugins += new ShiftPlugin("EU1")
plugins += new BranchPlugin("EU1")

plugins += new ExecutionUnitBase("EU2", writebackCountMax = 1)
plugins += new SrcPlugin("EU2", earlySrc = true)
plugins += new MulPlugin("EU2", writebackAt = 2, staticLatency = false)
plugins += new DivPlugin("EU2", writebackAt = 2)
plugins += new LoadPlugin("EU2")
plugins += new StorePlugin("EU2")
plugins += new EnvCallPlugin("EU2")(rescheduleAt = 2)
plugins += new CsrAccessPlugin("EU2")(
  decodeAt = 0,
  readAt = 1,
  writeAt = 2,
  writebackAt = 2,
  staticLatency = false
)




