//FRONTEND
plugins += new FrontendPlugin()
plugins += new DecompressorPlugin()
plugins += new DecoderPlugin()
plugins += new RfTranslationPlugin()
plugins += new RfDependencyPlugin()
plugins += new RfAllocationPlugin(riscv.IntRegFile)
plugins += new DispatchPlugin(
  slotCount = 32
)
