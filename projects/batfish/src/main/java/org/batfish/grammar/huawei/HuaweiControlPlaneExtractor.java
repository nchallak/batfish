package org.batfish.grammar.huawei;

import java.util.Set;
import java.util.TreeSet;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.batfish.common.Warnings;
import org.batfish.grammar.ControlPlaneExtractor;
import org.batfish.grammar.huawei.HuaweiParser.Huawei_configurationContext;
import org.batfish.grammar.huawei.HuaweiParser.S_sysnameContext;
import org.batfish.grammar.huawei.HuaweiParser.S_vlanContext;
import org.batfish.representation.huawei.HuaweiConfiguration;
import org.batfish.vendor.VendorConfiguration;

public class HuaweiControlPlaneExtractor extends HuaweiParserBaseListener
    implements ControlPlaneExtractor {
  private HuaweiConfiguration _configuration;
  private HuaweiCombinedParser _parser;
  private String _text;
  private Warnings _w;
  private final Set<String> _unimplementedFeatures;

  public HuaweiControlPlaneExtractor(String fileText, HuaweiCombinedParser hwParser,
      Warnings warnings) {
    _text = fileText;
    _parser = hwParser;
    _w = warnings;
    _unimplementedFeatures = new TreeSet<>();
  }

  @Override
  public void enterHuawei_configuration(Huawei_configurationContext ctx) {
    _configuration = new HuaweiConfiguration();
  }

  @Override
  public void exitS_sysname(S_sysnameContext ctx) {
    String hostname = ctx.name.getText();
    _configuration.setHostname(hostname);
  }

  @Override
  public void exitS_vlan(S_vlanContext ctx) {
    String hostname = _configuration.getHostname();
    String start = ctx.start.getText();
    String end = ctx.toString();
  }

  @Override public Set<String> getUnimplementedFeatures() {
    return _unimplementedFeatures;
  }

  @Override
  public VendorConfiguration getVendorConfiguration() {
    return _configuration;
  }

  @Override public void processParseTree(ParserRuleContext tree) {
    ParseTreeWalker walker = new ParseTreeWalker();
    walker.walk(this, tree);
  }
}
