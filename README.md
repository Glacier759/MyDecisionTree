MyDecisionTree
==============

建立在ID3算基础上实现决策树C4.5算法

##Input

>输入文件为.csv文件

>首行为各列的属性名Attribute

>之后各行为对应的记录Situation

>之间以逗号作为分隔符

##Output

>输出文件为output.xml

>根节点为'root' 接下来为'Decision'

>之后的信息为决策树的训练结果

>标签名为一个节点的属性名(Attribute)

>当属性选择记录(Situation)为value对应的值时

>则进入该标签的嵌套标签递归查询

>当递归找到结果时  标签所包含的Text即为最终的决策结果

####博客详解: http://glacierlx.sinaapp.com/?p=142
