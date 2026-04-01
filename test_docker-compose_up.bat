@echo off
echo Docker Compose を起動します...

REM --build を付けておくと、JSPなどの変更も自動で反映されるので便利です
docker-compose up --build -d

echo ---------------------------------------------------------------------------
echo docker-compose ps
echo ---------------------------------------------------------------------------
docker-compose ps
echo ---------------------------------------------------------------------------
echo.
echo 起動処理が完了しました。
echo サーバーの状態を確認するには docker-compose ps を確認してください。
pause

