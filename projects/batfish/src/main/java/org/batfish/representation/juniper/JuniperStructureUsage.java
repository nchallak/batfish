package org.batfish.representation.juniper;

import org.batfish.vendor.StructureUsage;

public enum JuniperStructureUsage implements StructureUsage {
  BGP_EXPORT_POLICY("bgp export policy-statement"),
  BGP_IMPORT_POLICY("bgp import policy-statement"),
  DHCP_RELAY_GROUP_ACTIVE_SERVER_GROUP("dhcp relay group active-server-group"),
  FORWARDING_TABLE_EXPORT_POLICY("forwarding-table export policy-statement"),
  GENERATED_ROUTE_POLICY("generated route policy-statement"),
  IKE_GATEWAY_EXTERNAL_INTERFACE("ike gateway external-interface"),
  IKE_GATEWAY_IKE_POLICY("ike gateway ike policy"),
  IKE_POLICY_IKE_PROPOSAL("ike policy ike proposal"),
  INTERFACE_INCOMING_FILTER("interface incoming firewall filter"),
  INTERFACE_OUTGOING_FILTER("interface outgoing firewall filter"),
  IPSEC_POLICY_IPSEC_PROPOSAL("ipsec policy ipsec proposal"),
  IPSEC_VPN_BIND_INTERFACE("ipsec vpn bind-interface"),
  IPSEC_VPN_IKE_GATEWAY("ipsec vpn ike gateway"),
  IPSEC_VPN_IPSEC_POLICY("ipsec vpn ipsec policy"),
  OSPF_EXPORT_POLICY("ospf export policy-statement"),
  SNMP_COMMUNITY_PREFIX_LIST("snmp community prefix-list");

  private final String _description;

  private JuniperStructureUsage(String description) {
    _description = description;
  }

  @Override
  public String getDescription() {
    return _description;
  }
}
