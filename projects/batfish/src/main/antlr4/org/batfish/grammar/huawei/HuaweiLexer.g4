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

// Lexer Rules

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

fragment
F_Whitespace
:
   ' '
   | '\t'
   | '\u000C'
;

NEWLINE
:
    F_Newline+
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
