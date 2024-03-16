# 書籍管理システムAPI

コーディングテスト用書籍管理API
> KotlinとSpring Boot、jOOQを利用し、書籍管理システムの構築をお願いします。
>- 書籍には著者の属性があり、書籍と著者の情報をRDBに登録・変更・検索ができる
>- 著者に紐づく本を取得できる

## API　コールサンプル
検索
'''
curl "http://localhost:8080/search?author=SteveJobs"
'''
登録
'''
curl -H 'Content-Type:application/json' -X POST -d '{"author":"testAuthor","title":"testTitle"}' 'http://localhost:8080/reg'
'''
更新
'''
curl -H 'Content-Type:application/json' -X PUT -d '{"id":1,"author":"yamazaki","title":"yamazakititle"}' 'http://localhost:8080/update'
'''

## TODO事項
UTテスト実装後の動作検証が未完了
jooqのコンテキストの初期化がうまく動作していない
'''
ParameterResolutionException : no parameterresolver registered for parameter
'''

