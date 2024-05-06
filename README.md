# jakoten
ja(**ja**va) ko(情報理**ko**u) ten(**te**mplate engi**n**e)です。

早稲田大学 情報理工学実験Bの課題用のちょっとしたJava製のテンプレートエンジンです。

次の機能を実装しています。
- 変数展開
- 繰り返し
- 条件式

サンプルソースは次の通りです。
https://github.com/iammytoo/jakoten/blob/master/Sample.java

文法は以下の通り
変数展開
```
<%= 変数 %>
```
繰り返し
```
<% for 変数 in 変数リスト %>
  内部コンテンツ
<% endfor %>
```

条件式
```
<% if フラッグ %>
    内部コンテンツ
<% endif %>
```

わからんかったら聞いてください...
