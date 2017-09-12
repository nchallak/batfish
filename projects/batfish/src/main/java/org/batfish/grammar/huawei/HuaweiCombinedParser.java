package org.batfish.grammar.huawei;

import org.batfish.config.Settings;
import org.batfish.grammar.BatfishCombinedParser;
import org.batfish.grammar.huawei.HuaweiParser.Huawei_configurationContext;

public class HuaweiCombinedParser extends BatfishCombinedParser<HuaweiParser, HuaweiLexer> {

  @SuppressWarnings("fallthrough")
  public HuaweiCombinedParser(String input, Settings settings) {
    super(HuaweiParser.class, HuaweiLexer.class, input, settings);
  }

  @Override
  public Huawei_configurationContext parse() {
    return _parser.huawei_configuration();
  }
}
