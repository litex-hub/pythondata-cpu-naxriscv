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

println(memoryRegions.mkString("\n"))
def ioRange (address : UInt) : Bool = memoryRegions.filter(_.isIo).map(_.mapping.hit(address)).orR
def fetchRange (address : UInt) : Bool = memoryRegions.filter(_.isExecutable).map(_.mapping.hit(address)).orR
def peripheralRange (address : UInt) : Bool = memoryRegions.filter(_.onPeripheral).map(_.mapping.hit(address)).orR

plugins ++= Config.plugins(
  xlen = xlen,
  ioRange = ioRange,
  fetchRange = fetchRange,
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


