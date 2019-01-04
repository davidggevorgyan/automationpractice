#!/usr/bin/env bash
curl -T ./target/report/report.html --connect-timeout 5 --max-time 10 --retry 5 --retry-delay 0 --retry-max-time 10 ftp://$ftpusername:$ftppassword@$ftppath/htdocs/