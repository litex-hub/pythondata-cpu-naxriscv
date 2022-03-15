import spinal.core._
import naxriscv.compatibility._
import naxriscv.frontend._
import naxriscv.fetch._
import naxriscv.misc._
import naxriscv.execute._
import naxriscv.fetch._
import naxriscv.lsu._
import naxriscv.prediction._
import naxriscv.utilities._
import naxriscv._

def ioRange   (address : UInt) : Bool = address(30, 2 bits) =/= U"01"
def fetchRange (address : UInt) : Bool = SizeMapping(0x40000000, 0x10000000).hit(address) || SizeMapping(0, 0x00020000).hit(address)

plugins += new DocPlugin()
plugins += new MmuPlugin(
  spec = xlen match {
     case 32 => MmuSpec.sv32
     case 64 => MmuSpec.sv39
  },
  ioRange = ioRange,
  fetchRange = fetchRange,
  physicalWidth = 32
)


//MISC
plugins += new RobPlugin(
  robSize = 64,
  completionWithReg = false
)
plugins += new CommitPlugin(
  commitCount = 2,
  ptrCommitRetimed = true
)
plugins += new RegFilePlugin(
  spec = riscv.IntRegFile,
  physicalDepth = 64,
  bankCount = 1
)
plugins += new CommitDebugFilterPlugin(List(4, 8, 12))
plugins += new CsrRamPlugin()
plugins += new PrivilegedPlugin(PrivilegedConfig.full)
plugins += new PerformanceCounterPlugin(
  additionalCounterCount = 4,
  bufferWidth            = 6
)


