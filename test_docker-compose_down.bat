@echo off
echo Docker コンテナを停止・削除します...

# コンテナを停止し、作成されたリソースを削除
docker-compose down -v

echo.
echo 停止処理が完了しました。
pause
