#!/usr/bin/env bash
curl -T ./target/report/report.html ftp://$ftpusername:$ftppassword@$ftppath/www/