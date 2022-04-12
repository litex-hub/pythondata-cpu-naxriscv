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
import naxriscv.debug._
import naxriscv._

def ioRange   (address : UInt) : Bool = address(30, 2 bits) =/= U"01"
def fetchRange (address : UInt) : Bool = SizeMapping(0x40000000, 0x10000000).hit(address) || SizeMapping(0, 0x00020000).hit(address)

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
  withDebug = debug,
  withEmbeddedJtagTap = jtagTap,
  withEmbeddedJtagInstruction = jtagInstruction
)

plugins.foreach{
  case p : EmbeddedJtagPlugin => {
    if(!p.withTap) p.noTapCd.load(ClockDomain.external("jtag_instruction", withReset = false))
    p.debugCd.load(ClockDomain.current.copy(reset = Bool().setName("debug_reset")))
  }
  case _ =>
}
