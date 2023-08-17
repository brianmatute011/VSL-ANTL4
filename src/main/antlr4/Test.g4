grammar Test;
@headers{
package org.example.parser;
}

program: statement+;

statement: 'I' | 'K';