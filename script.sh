#!/bin/sh

CLS="DatoGeografico"
PACK="a3b.climate.magazzeno.$CLS"
FILES=$(find ./src -name '*.java' -print)

for f in $FILES
do
	sed "s/link $CLS/link $PACK/" $f > $f.tmp
	cat $f.tmp > $f
	rm $f.tmp
done

