//EXECUTION UNITES
plugins += new ExecutionUnitBase("EU0", readPhysRsFromQueue = true)
plugins += new IntFormatPlugin("EU0")
plugins += new SrcPlugin("EU0", earlySrc = true)
plugins += new IntAluPlugin("EU0", aluStage = 0)
plugins += new ShiftPlugin("EU0" , aluStage = 0)
if(xlen == 64) Tweek.euWritebackAt(plugins, "EU0", 1)

plugins += new ExecutionUnitBase("EU1", writebackCountMax = 1, readPhysRsFromQueue = true)
plugins += new IntFormatPlugin("EU1")
plugins += new SrcPlugin("EU1", earlySrc = true)
plugins += new MulPlugin("EU1", writebackAt = 2, staticLatency = false)
plugins += new DivPlugin("EU1", writebackAt = 2)
plugins += new LoadPlugin("EU1")
plugins += new StorePlugin("EU1")
plugins += new BranchPlugin("EU1", writebackAt = 2, staticLatency = false)
plugins += new EnvCallPlugin("EU1")(rescheduleAt = 2)
plugins += new CsrAccessPlugin("EU1")(
  writebackAt = 2
)



