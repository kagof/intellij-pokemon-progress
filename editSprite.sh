#!/bin/bash

SCRIPT_DIR=$(dirname "$0")
DEFAULT_RESIZE='32x32'
DEFAULT_EXTENT='32x32'
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

usage() {
  echo "
Utility for creating animated sprites from still PNGs.

usage:

editSprite.sh --help

  Show this message

editSprite.sh spriteName [resize] [extent] [delay] [path]

  - spriteName: the name of the sprite. There should be matching spriteName_1.png, spriteName_2.png, ... files in the path, and a spriteName.gif will be created in the path.
      NOTE: existing files named spriteName.gif or spriteName_r.gif in the path will be overwritten
  - resize: shrink/expand each image before conversion.
      DEFAULT: ${DEFAULT_RESIZE}
  - extent: expand the resulting gif post conversion, by padding the border with transparency.
      DEFAULT: ${DEFAULT_EXTENT}
  - delay: timing between frames, in ms.
      DEFAULT: ${DEFAULT_DELAY}
  - path: path of the sprites to create & use.
      DEFAULT: ${DEFAULT_PATH}
  "
}

if ! command -v magick &>/dev/null; then
  echo "This script requires ImageMagick (https://imagemagick.org/script/index.php)"
  exit 1
elif [ "$1" = '--help' ]; then
  usage
  exit 0
fi

[ -z "$1" ] && usage && exit 2

spriteName=$1
resize=${2:-${DEFAULT_RESIZE}}
extent=${3:-${DEFAULT_EXTENT}}
delay=${4:-${DEFAULT_DELAY}}
path=${5:-${DEFAULT_PATH}}

# create initial gif
convert \
  -delay "$delay" \
  -dispose Background \
  -resize "$resize" \
  -background none \
  -gravity center \
  -extent "$extent" \
  "${path}/${spriteName}*.png" \
  "${path}/${spriteName}.gif"

checkExitCode 'unable to convert sprite (run editSprite.sh --help for usage)'

# create reversed gif
convert \
  -flop \
  "${path}/${spriteName}.gif" \
  "${path}/${spriteName}_r.gif"

checkExitCode 'unable to create reversed sprite (run editSprite.sh --help for usage)'

exit 0
