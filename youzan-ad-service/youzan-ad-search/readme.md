广告数据索引的设计
--------------------------------------------
设计索引的目的就是为了加快检索的速度，将原始数据抽象，规划出合理的字段，再内存中构建广告数据索引。记住，并不是所有的数据都需要刚在索引里

正向索引
--------------------------------------------
核心思想事通过一个键找到一个对象，且这种关系是确定的，即唯一键对应到唯一的对象。
（1）推广计划
（2）推广单元
（3）创意

倒排索引
--------------------------------------------
核心思想是通过内容去确定包含关系的对象。
（1）创意与推广单元的关联对象
（2）推广单元地域限制
（3）推广单元兴趣的限制
（4）推广单元关键词的限制
