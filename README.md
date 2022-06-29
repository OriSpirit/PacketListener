# PacketListener

Utility mod to see your packet status

## Commands
*Note: Packets are discarded until added again, in which it's removed

- /packet s [name]: Catches send packets with the names. No args to show current tracked packets.
- /packet r [name]: Catches received packet with the names. No args to show current tracked packets.
- /packet shutup: Toggles whether in-game messages should be sent when packet is received.

Additionally, use /packet discard <s/r> [name] to discard all incoming packets until you remove again.

## Building

Built via Intellij IDEA under JDK 8u211.
