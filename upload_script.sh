#!/usr/bin/env bash
sleep 5
curl -T ./target/report/report.html ftp://$ftpusername:$ftppassword@$ftppath/htdocs/