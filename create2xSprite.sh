#!/bin/bash

# This tool is for migrating existing sprites to @2x versions. In order to use it
# simply measure the size of the non-transparent part of the original PNG image,
# and the same size on the gif that was created from it.

DEFAULT_EXTENT=32
SCRIPT_DIR=$(dirname "$0")
DEFAULT_DELAY='50'
DEFAULT_PATH=${SCRIPT_DIR}'/src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites'

checkExitCode() {
  exitCode="$?"
  if [ "$exitCode" -ne "0" ]; then
    echo
    echo $1
    exit "$exitCode"
  fi
}

if ! command -v magick &>/dev/null; then
  echo "This script requires ImageMagick (https://imagemagick.org/script/index.php)"
  exit 1
fi

[ -z "$1" ] && usage && exit 2
[ -z "$2" ] && usage && exit 2
[ -z "$3" ] && usage && exit 2

spriteName=$1
originalHeight=$2 # the height of the non-transparent part of the original PNG image
newHeight=$3      # the height of the non-transparent part of the resulting GIF image
percentage=$(bc -l <<<"2*100*${newHeight}/${originalHeight}")
extent=${4:-${DEFAULT_EXTENT}}
extent2=$((2 * extent))
delay=${4:-${DEFAULT_DELAY}}
path=${5:-${DEFAULT_PATH}}

# create @2x gif
convert \
  -delay "$delay" \
  -dispose Background \
  -resize "${percentage}%" \
  -background none \
  -gravity center \
  -extent "${extent2}x${extent2}" \
  "${path}/${spriteName}_*.png" \
  "${path}/${spriteName}@2x.gif"

checkExitCode 'unable to create @2x sprite'

# create reversed @2x gif
convert \
  -flop \
  "${path}/${spriteName}@2x.gif" \
  "${path}/${spriteName}_r@2x.gif"

checkExitCode 'unable to create reversed @2x sprite'

exit 0
