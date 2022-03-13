#!/bin/bash

SCRIPT_DIR=$(dirname "$0")
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
Utility for creating SVGs for a given Pokemon's PNG sprite(s).

usage:

editSprite.sh --help

  Show this message

editSprite.sh pokemon [path]

  - pokemon: the name of the pokemon. There should be matching pokemon_1.png, pokemon_2.png, ... files in the path.
      NOTE: existing files named spriteName_1.svg, spriteName_2.svg, ... will be overwritten.
  - path: path of the sprites to create & use.
      DEFAULT: ${DEFAULT_PATH}
  "
}

if ! command -v psvg &>/dev/null; then
  echo "This script requires pixel-perfect-svg (https://github.com/kagof/pixel-perfect-svg)"
  exit 1
elif [ "$1" = '--help' ]; then
  usage
  exit 0
fi

[ -z "$1" ] && usage && exit 2

pokemon=$1
path=${2:-${DEFAULT_PATH}}

for png in ${path}/${pokemon}_*.png; do
    psvg -mq -i $png -o ${png%.*}.svg
    checkExitCode 'unable to convert sprite $png (run editSprite.sh --help for usage)'
done

exit 0
