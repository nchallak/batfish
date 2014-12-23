package batfish.representation.juniper;

import batfish.representation.IpAccessListLine;
import batfish.representation.Prefix;

public final class FwFromSourceAddress extends FwFrom {

   /**
    *
    */
   private static final long serialVersionUID = 1L;

   private final Prefix _prefix;

   public FwFromSourceAddress(Prefix prefix) {
      _prefix = prefix;
   }

   public Prefix getPrefix() {
      return _prefix;
   }

   @Override
   public void applyTo(IpAccessListLine line) {
      line.getSourceIpRanges().add(_prefix);
   }

}