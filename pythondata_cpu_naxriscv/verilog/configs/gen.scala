import spinal.core._
import spinal.lib._
import naxriscv.compatibility._
import naxriscv.frontend._
import naxriscv.fetch._
import naxriscv.misc._
import naxriscv.execute._
import naxriscv.fetch._
import naxriscv.lsu._
import naxriscv.prediction._
import naxriscv.utilities._
import naxriscv.debug._
import naxriscv._

val memoryRegionsNoIo = memoryRegions.filter(!_.isIo) //Remove all IO specifications
def ioRange (address : UInt) : Bool = memoryRegionsNoIo.filter(!_.isCachable).map(_.mapping.hit(address)).orR || SizeMapping(0xF0010000l, 0x10000).hit(address) || SizeMapping(0xF0C00000l, 0x400000).hit(address)
def fetchRange (address : UInt) : Bool = memoryRegionsNoIo.filter(_.isExecutable).map(_.mapping.hit(address)).orR
def peripheralRange (address : UInt) : Bool = memoryRegionsNoIo.filter(_.onPeripheral).map(_.mapping.hit(address)).orR
def memoryRange (address : UInt) : Bool = memoryRegionsNoIo.filter(_.isCachable).map(_.mapping.hit(address)).orR

plugins ++= Config.plugins(
  xlen = xlen,
  ioRange = ioRange,
  fetchRange = fetchRange,
  memRange   = memoryRange,
  resetVector = resetVector,
  aluCount    = arg("alu-count", 2),
  decodeCount = arg("decode-count", 2),
  debugTriggers = 0,
  withRvc = arg("rvc", false),
  withLoadStore = true,
  withMmu = arg("mmu", true),
  withFloat  = arg("rvf", false),
  withDouble = arg("rvd", false),
  withDebug = debug,
  withEmbeddedJtagTap = false,
  withEmbeddedJtagInstruction = false,
  withCoherency = true,      
  withRdTime = true
)
//plugins += new XilinxDebug()
//plugins += new DebugScratchCsrPlugin(3)


