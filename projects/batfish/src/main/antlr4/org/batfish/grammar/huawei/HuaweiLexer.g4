lexer grammar HuaweiLexer;

options {
    superClass='org.batfish.grammar.BatfishLexer';
}

@members {
private int lastTokenType = -1;
private boolean enableIPV6_ADDRESS = true;
private boolean enableIP_ADDRESS = true;
private boolean enableDEC = true;
private boolean enableACL_NUM = false;
private boolean enableCOMMUNITY_LIST_NUM = false;
private boolean _inAccessList = false;
private boolean inCommunitySet = false;

@Override
public void emit(Token token) {
    super.emit(token);
    if (token.getChannel() != HIDDEN) {
       lastTokenType = token.getType();
    }
}

}

// Lexer declarations

SYSNAME
:
    'sysname'
;

VLAN
:
    'vlan'
;
BATCH
:
    'batch'
;
TO
:
    'to'
;

INTERFACE
:
    'interface' ->pushMode(M_Interface)
;

IP
:
    'ip'
;

ADDRESS
:
    'address'
;

VRRP
:
    'vrrp'
;

VRID
:
    'vrid'
;

VIRTUALIP
:
    'virtual-ip'
;

VRRP_PRIORITY
:
    'priority'
;

VRRP_PREEMPT_TIMER_DELAY
:
    'preempt timer delay'
;

// Lexer expressions
IP_ADDRESS
:
   F_DecByte '.'  F_DecByte '.' F_DecByte '.' F_DecByte
;

NAME
:
    [a-zA-Z_]+NUMBER?
;

NUMBER
:
    F_Digit+
;

RETURN : 'return' WS? NEWLINE? -> channel ( HIDDEN ) ;

WS
:
    F_Whitespace+ -> channel ( HIDDEN )
;

COMMENT
:
    '#' F_NonNewline* F_Newline+ -> channel(HIDDEN)
;

NEWLINE
:
    F_Newline+
;

fragment
F_DecByte
:
    (F_Digit F_Digit F_Digit)
    | (F_Digit F_Digit)
    | (F_Digit)
;

fragment
F_Whitespace
:
   ' '
   | '\t'
   | '\u000C'
;

fragment
F_Newline
:
    [\n\r]
;

fragment
F_NonNewline
:
    ~[\n\r]
;

fragment
F_Digit
:
    [0-9]
;

mode M_Interface;
M_Interface_NEWLINE
:
   F_Newline+

   -> type ( NEWLINE ), popMode
;

M_Interface_INTERFACE
:
   'interface' -> type ( INTERFACE )
;

M_Interface_IP
:
   'ip' -> type ( IP )
;

M_Interface_ADDRESS
:
   'address' -> type ( ADDRESS )
;

M_Interface_IP_ADDRESS
:
   F_DecByte '.' F_DecByte '.' F_DecByte '.' F_DecByte -> type ( IP_ADDRESS )
;

M_Interface_WS
:
   F_Whitespace+ -> channel ( HIDDEN )
;

M_Interface_NAME
:
    [a-zA-Z_]+NUMBER? ->type ( NAME )
;