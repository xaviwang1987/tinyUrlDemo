{\rtf1\ansi\ansicpg936\cocoartf2639
\cocoatextscaling0\cocoaplatform0{\fonttbl\f0\fswiss\fcharset0 Helvetica;}
{\colortbl;\red255\green255\blue255;}
{\*\expandedcolortbl;;}
\paperw11900\paperh16840\margl1440\margr1440\vieww26480\viewh17500\viewkind0
\pard\tx566\tx1133\tx1700\tx2267\tx2834\tx3401\tx3968\tx4535\tx5102\tx5669\tx6236\tx6803\pardirnatural\partightenfactor0

\f0\fs24 \cf0 # Performance test methodology and result\
## Methodology\
* Framework: JMH\
* Scope: All 3 modes - Incremental ID, Random ID, 62 bits Incremental ID \
* Coverage: Read (both hash hit and not hit) and Write Operations\
## Runtime\
OS: MacOS Monterey v12.6.1\
Processor: 2.6 GHz Intel Core i7\
Memory: 16 GB 2667 MHz DDR4\
\
## Benchmarks\
### Average Time\
\
#### Mode - Single Thread\
| Annotation                                   | Value   |\
| -------------------------------------------- | ------- |\
| Warmup iteration                             | 3       |\
| Number of measurement iterations             | 3       |\
| Time of each measurement iteration           | 5       |\
| Time unit for measurement iteration duration | Nano Seconds |\
| Number of threads to run                     | 1       |\
| Forking parameters                                             | 1        |\
\
Result - Run complete. Total time: 00:04:32\
\
| Benchmark | Mode | Cnt | Score | Error | Units |\
| --------- | ---- | --- | ----- | ----- | ----- |\
| IncrementalIdTransformerReadBenchmarkTest.testHit      |  avgt    | 3    |   3.533    |  \'b1  29.478     |    ns/op   |\
| IncrementalIdTransformerReadBenchmarkTest.testNotHit           |   avgt   | 3   |  2.209     |  \'b1   0.847     |  ns/op     |\
| IncrementalIdTransformerWriteBenchmarkTest.test          |   avgt   |  3   |  190.172      |  \'b1 442.603     |     ns/op  |\
| RandomIdTransformerReadBenchmarkTest.testHit          |   avgt   |  3   |   3.269    |    \'b1  18.951    |    ns/op   |\
| RandomIdTransformerReadBenchmarkTest.testNotHit           |   avgt   | 3    |  2.632     |   \'b1  13.296      |   ns/op    |\
| RandomIdTransformerWriteBenchmarkTest.test           |   avgt   |  3   |   278.839    |   \'b1 661.702    |   ns/op    |\
| SixtyTwoBitsIdTransformerReadBenchmarkTest.testHit          |   avgt   |  3   |   4.588     |    \'b1  12.335    | ns/op      |\
| SixtyTwoBitsIdTransformerReadBenchmarkTest.testNotHit          |   avgt   |  3   | 3.236      |    \'b1  18.606    | ns/op      |\
| SixtyTwoBitsIdTransformerWriteBenchmarkTest.test          |   avgt   |  3   |  93.776     |    \'b1 283.696     |   ns/op    |\
\
#### Mode - 1K Thread\
| Annotation                                   | Value   |\
| -------------------------------------------- | ------- |\
| Warmup iteration                             | 3       |\
| Number of measurement iterations             | 3       |\
| Time of each measurement iteration           | 5       |\
| Time unit for measurement iteration duration | Nano Seconds |\
| Number of threads to run                     | 1000       |\
| Forking parameters                                             | 1        |\
Result - Run complete. Total time: 00:06:35\
\
| Benchmark | Mode | Cnt | Score | Error | Units |\
| --------- | ---- | --- | ----- | ----- | ----- |\
| IncrementalIdTransformerReadBenchmarkTest.testHit      |  avgt    | 3    |   422.402    |  \'b1       838.662     |    ns/op   |\
| IncrementalIdTransformerReadBenchmarkTest.testNotHit           |   avgt   | 3   |  347.834     |  \'b1   3973.189     |  ns/op     |\
| IncrementalIdTransformerWriteBenchmarkTest.test          |   avgt   |  3   |  70880.934     |  \'b1 1246933.582     |     ns/op  |\
| RandomIdTransformerReadBenchmarkTest.testHit          |   avgt   |  3   |   354.454    |    \'b1 1152.323    |    ns/op   |\
| RandomIdTransformerReadBenchmarkTest.testNotHit           |   avgt   | 3    | 956.762    |   \'b1 21375.438     |   ns/op    |\
| RandomIdTransformerWriteBenchmarkTest.test           |   avgt   |  3   |   761186.826    |   \'b1 23998862.14    |   ns/op    |\
| SixtyTwoBitsIdTransformerReadBenchmarkTest.testHit          |   avgt   |  3   |   262.191     |    \'b1 933.035    | ns/op      |\
| SixtyTwoBitsIdTransformerReadBenchmarkTest.testNotHit          |   avgt   |  3   | 305.035      |    \'b1  955.485   | ns/op      |\
| SixtyTwoBitsIdTransformerWriteBenchmarkTest.test          |   avgt   |  3   | 292032.387     |    \'b1 6623863.072     |   ns/op    |\
\
Result shows in multiple thread mode, the read capability scales up in linear mode, while the write capability will be impacted more, since the algorithm may have more conflict when updating ID information incrementally or randomly.\
\
### Other Benchmarks\
This is also doable with the same methodolgy as above via changing the JMH benchmark mode. I've also run in mode "ALL", please refer to the appendix for details of that test.\
\
## Appendix\
Result of erformance test in mode "ALL".\
\
Run complete. Total time: 00:14:31\
\
Benchmark                                                                   Mode    Cnt        Score       Error   Units\
IncrementalIdTransformerReadBenchmarkTest.testHit                          thrpt      3        0.372 \'b1     0.180  ops/ns\
IncrementalIdTransformerReadBenchmarkTest.testNotHit                       thrpt      3        0.457 \'b1     0.055  ops/ns\
IncrementalIdTransformerWriteBenchmarkTest.test                            thrpt      3        0.005 \'b1     0.047  ops/ns\
RandomIdTransformerReadBenchmarkTest.testHit                               thrpt      3        6.656 \'b1    16.335  ops/ns\
RandomIdTransformerReadBenchmarkTest.testNotHit                            thrpt      3        0.376 \'b1     2.596  ops/ns\
RandomIdTransformerWriteBenchmarkTest.test                                 thrpt      3        0.007 \'b1     0.002  ops/ns\
SixtyTwoBitsIdTransformerReadBenchmarkTest.testHit                         thrpt      3        0.231 \'b1     2.301  ops/ns\
SixtyTwoBitsIdTransformerReadBenchmarkTest.testNotHit                      thrpt      3        0.480 \'b1     2.406  ops/ns\
SixtyTwoBitsIdTransformerWriteBenchmarkTest.test                           thrpt      3        0.008 \'b1     0.069  ops/ns\
IncrementalIdTransformerReadBenchmarkTest.testHit                           avgt      3        2.584 \'b1     0.120   ns/op\
IncrementalIdTransformerReadBenchmarkTest.testNotHit                        avgt      3        3.357 \'b1     6.117   ns/op\
IncrementalIdTransformerWriteBenchmarkTest.test                             avgt      3      105.735 \'b1    64.651   ns/op\
RandomIdTransformerReadBenchmarkTest.testHit                                avgt      3      376.453 \'b1  4172.586   ns/op\
RandomIdTransformerReadBenchmarkTest.testNotHit                             avgt      3        1.828 \'b1     0.085   ns/op\
RandomIdTransformerWriteBenchmarkTest.test                                  avgt      3      192.821 \'b1  1575.149   ns/op\
SixtyTwoBitsIdTransformerReadBenchmarkTest.testHit                          avgt      3        3.080 \'b1    17.456   ns/op\
SixtyTwoBitsIdTransformerReadBenchmarkTest.testNotHit                       avgt      3        2.592 \'b1     0.799   ns/op\
SixtyTwoBitsIdTransformerWriteBenchmarkTest.test                            avgt      3      102.063 \'b1   103.457   ns/op\
IncrementalIdTransformerReadBenchmarkTest.testHit                         sample     22       52.500 \'b1    18.912   ns/op\
IncrementalIdTransformerReadBenchmarkTest.testHit:testHit\'b7p0.00           sample              34.000               ns/op\
IncrementalIdTransformerReadBenchmarkTest.testHit:testHit\'b7p0.50           sample              39.000               ns/op\
IncrementalIdTransformerReadBenchmarkTest.testHit:testHit\'b7p0.90           sample              84.000               ns/op\
IncrementalIdTransformerReadBenchmarkTest.testHit:testHit\'b7p0.95           sample             113.350               ns/op\
IncrementalIdTransformerReadBenchmarkTest.testHit:testHit\'b7p0.99           sample             118.000               ns/op\
IncrementalIdTransformerReadBenchmarkTest.testHit:testHit\'b7p0.999          sample             118.000               ns/op\
IncrementalIdTransformerReadBenchmarkTest.testHit:testHit\'b7p0.9999         sample             118.000               ns/op\
IncrementalIdTransformerReadBenchmarkTest.testHit:testHit\'b7p1.00           sample             118.000               ns/op\
IncrementalIdTransformerReadBenchmarkTest.testNotHit                      sample     21       56.714 \'b1    23.506   ns/op\
IncrementalIdTransformerReadBenchmarkTest.testNotHit:testNotHit\'b7p0.00     sample              34.000               ns/op\
IncrementalIdTransformerReadBenchmarkTest.testNotHit:testNotHit\'b7p0.50     sample              38.000               ns/op\
IncrementalIdTransformerReadBenchmarkTest.testNotHit:testNotHit\'b7p0.90     sample             100.600               ns/op\
IncrementalIdTransformerReadBenchmarkTest.testNotHit:testNotHit\'b7p0.95     sample             131.200               ns/op\
IncrementalIdTransformerReadBenchmarkTest.testNotHit:testNotHit\'b7p0.99     sample             134.000               ns/op\
IncrementalIdTransformerReadBenchmarkTest.testNotHit:testNotHit\'b7p0.999    sample             134.000               ns/op\
IncrementalIdTransformerReadBenchmarkTest.testNotHit:testNotHit\'b7p0.9999   sample             134.000               ns/op\
IncrementalIdTransformerReadBenchmarkTest.testNotHit:testNotHit\'b7p1.00     sample             134.000               ns/op\
IncrementalIdTransformerWriteBenchmarkTest.test                           sample     28      195.464 \'b1    88.317   ns/op\
IncrementalIdTransformerWriteBenchmarkTest.test:test\'b7p0.00                sample             118.000               ns/op\
IncrementalIdTransformerWriteBenchmarkTest.test:test\'b7p0.50                sample             144.500               ns/op\
IncrementalIdTransformerWriteBenchmarkTest.test:test\'b7p0.90                sample             463.900               ns/op\
IncrementalIdTransformerWriteBenchmarkTest.test:test\'b7p0.95                sample             571.100               ns/op\
IncrementalIdTransformerWriteBenchmarkTest.test:test\'b7p0.99                sample             608.000               ns/op\
IncrementalIdTransformerWriteBenchmarkTest.test:test\'b7p0.999               sample             608.000               ns/op\
IncrementalIdTransformerWriteBenchmarkTest.test:test\'b7p0.9999              sample             608.000               ns/op\
IncrementalIdTransformerWriteBenchmarkTest.test:test\'b7p1.00                sample             608.000               ns/op\
RandomIdTransformerReadBenchmarkTest.testHit                              sample  15572      441.000 \'b1  1081.190   ns/op\
RandomIdTransformerReadBenchmarkTest.testHit:testHit\'b7p0.00                sample               1.000               ns/op\
RandomIdTransformerReadBenchmarkTest.testHit:testHit\'b7p0.50                sample              91.000               ns/op\
RandomIdTransformerReadBenchmarkTest.testHit:testHit\'b7p0.90                sample             166.000               ns/op\
RandomIdTransformerReadBenchmarkTest.testHit:testHit\'b7p0.95                sample             211.350               ns/op\
RandomIdTransformerReadBenchmarkTest.testHit:testHit\'b7p0.99                sample             317.000               ns/op\
RandomIdTransformerReadBenchmarkTest.testHit:testHit\'b7p0.999               sample             623.562               ns/op\
RandomIdTransformerReadBenchmarkTest.testHit:testHit\'b7p0.9999              sample         2373280.384               ns/op\
RandomIdTransformerReadBenchmarkTest.testHit:testHit\'b7p1.00                sample         5111808.000               ns/op\
RandomIdTransformerReadBenchmarkTest.testNotHit                           sample     31       68.452 \'b1    49.934   ns/op\
RandomIdTransformerReadBenchmarkTest.testNotHit:testNotHit\'b7p0.00          sample              34.000               ns/op\
RandomIdTransformerReadBenchmarkTest.testNotHit:testNotHit\'b7p0.50          sample              43.000               ns/op\
RandomIdTransformerReadBenchmarkTest.testNotHit:testNotHit\'b7p0.90          sample             138.400               ns/op\
RandomIdTransformerReadBenchmarkTest.testNotHit:testNotHit\'b7p0.95          sample             297.200               ns/op\
RandomIdTransformerReadBenchmarkTest.testNotHit:testNotHit\'b7p0.99          sample             428.000               ns/op\
RandomIdTransformerReadBenchmarkTest.testNotHit:testNotHit\'b7p0.999         sample             428.000               ns/op\
RandomIdTransformerReadBenchmarkTest.testNotHit:testNotHit\'b7p0.9999        sample             428.000               ns/op\
RandomIdTransformerReadBenchmarkTest.testNotHit:testNotHit\'b7p1.00          sample             428.000               ns/op\
RandomIdTransformerWriteBenchmarkTest.test                                sample     32      260.219 \'b1    97.770   ns/op\
RandomIdTransformerWriteBenchmarkTest.test:test\'b7p0.00                     sample             191.000               ns/op\
RandomIdTransformerWriteBenchmarkTest.test:test\'b7p0.50                     sample             206.000               ns/op\
RandomIdTransformerWriteBenchmarkTest.test:test\'b7p0.90                     sample             468.100               ns/op\
RandomIdTransformerWriteBenchmarkTest.test:test\'b7p0.95                     sample             757.650               ns/op\
RandomIdTransformerWriteBenchmarkTest.test:test\'b7p0.99                     sample             861.000               ns/op\
RandomIdTransformerWriteBenchmarkTest.test:test\'b7p0.999                    sample             861.000               ns/op\
RandomIdTransformerWriteBenchmarkTest.test:test\'b7p0.9999                   sample             861.000               ns/op\
RandomIdTransformerWriteBenchmarkTest.test:test\'b7p1.00                     sample             861.000               ns/op\
SixtyTwoBitsIdTransformerReadBenchmarkTest.testHit                        sample     28       84.571 \'b1    99.315   ns/op\
SixtyTwoBitsIdTransformerReadBenchmarkTest.testHit:testHit\'b7p0.00          sample              34.000               ns/op\
SixtyTwoBitsIdTransformerReadBenchmarkTest.testHit:testHit\'b7p0.50          sample              45.500               ns/op\
SixtyTwoBitsIdTransformerReadBenchmarkTest.testHit:testHit\'b7p0.90          sample             129.400               ns/op\
SixtyTwoBitsIdTransformerReadBenchmarkTest.testHit:testHit\'b7p0.95          sample             515.700               ns/op\
SixtyTwoBitsIdTransformerReadBenchmarkTest.testHit:testHit\'b7p0.99          sample             792.000               ns/op\
SixtyTwoBitsIdTransformerReadBenchmarkTest.testHit:testHit\'b7p0.999         sample             792.000               ns/op\
SixtyTwoBitsIdTransformerReadBenchmarkTest.testHit:testHit\'b7p0.9999        sample             792.000               ns/op\
SixtyTwoBitsIdTransformerReadBenchmarkTest.testHit:testHit\'b7p1.00          sample             792.000               ns/op\
SixtyTwoBitsIdTransformerReadBenchmarkTest.testNotHit                     sample     25       50.400 \'b1    15.992   ns/op\
SixtyTwoBitsIdTransformerReadBenchmarkTest.testNotHit:testNotHit\'b7p0.00    sample              33.000               ns/op\
SixtyTwoBitsIdTransformerReadBenchmarkTest.testNotHit:testNotHit\'b7p0.50    sample              37.000               ns/op\
SixtyTwoBitsIdTransformerReadBenchmarkTest.testNotHit:testNotHit\'b7p0.90    sample              86.600               ns/op\
SixtyTwoBitsIdTransformerReadBenchmarkTest.testNotHit:testNotHit\'b7p0.95    sample              95.700               ns/op\
SixtyTwoBitsIdTransformerReadBenchmarkTest.testNotHit:testNotHit\'b7p0.99    sample              96.000               ns/op\
SixtyTwoBitsIdTransformerReadBenchmarkTest.testNotHit:testNotHit\'b7p0.999   sample              96.000               ns/op\
SixtyTwoBitsIdTransformerReadBenchmarkTest.testNotHit:testNotHit\'b7p0.9999  sample              96.000               ns/op\
SixtyTwoBitsIdTransformerReadBenchmarkTest.testNotHit:testNotHit\'b7p1.00    sample              96.000               ns/op\
SixtyTwoBitsIdTransformerWriteBenchmarkTest.test                          sample     24     1071.375 \'b1  2856.068   ns/op\
SixtyTwoBitsIdTransformerWriteBenchmarkTest.test:test\'b7p0.00               sample             146.000               ns/op\
SixtyTwoBitsIdTransformerWriteBenchmarkTest.test:test\'b7p0.50               sample             177.000               ns/op\
SixtyTwoBitsIdTransformerWriteBenchmarkTest.test:test\'b7p0.90               sample            1325.000               ns/op\
SixtyTwoBitsIdTransformerWriteBenchmarkTest.test:test\'b7p0.95               sample           14162.500               ns/op\
SixtyTwoBitsIdTransformerWriteBenchmarkTest.test:test\'b7p0.99               sample           18432.000               ns/op\
SixtyTwoBitsIdTransformerWriteBenchmarkTest.test:test\'b7p0.999              sample           18432.000               ns/op\
SixtyTwoBitsIdTransformerWriteBenchmarkTest.test:test\'b7p0.9999             sample           18432.000               ns/op\
SixtyTwoBitsIdTransformerWriteBenchmarkTest.test:test\'b7p1.00               sample           18432.000               ns/op\
IncrementalIdTransformerReadBenchmarkTest.testHit                             ss      3     1509.000 \'b1  5834.541   ns/op\
IncrementalIdTransformerReadBenchmarkTest.testNotHit                          ss      3     1407.000 \'b1  3659.265   ns/op\
IncrementalIdTransformerWriteBenchmarkTest.test                               ss      3     6977.000 \'b1 23886.510   ns/op\
RandomIdTransformerReadBenchmarkTest.testHit                                  ss      3      299.938 \'b1  1590.075   ns/op\
RandomIdTransformerReadBenchmarkTest.testNotHit                               ss      3     1209.667 \'b1  5694.331   ns/op\
RandomIdTransformerWriteBenchmarkTest.test                                    ss      3    15251.667 \'b1 99167.014   ns/op\
SixtyTwoBitsIdTransformerReadBenchmarkTest.testHit                            ss      3     2187.667 \'b1 14105.033   ns/op\
SixtyTwoBitsIdTransformerReadBenchmarkTest.testNotHit                         ss      3     1390.000 \'b1  5066.502   ns/op\
SixtyTwoBitsIdTransformerWriteBenchmarkTest.test                              ss      3     9728.000 \'b1  1392.272   ns/op}